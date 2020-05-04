#!/bin/bash
# Build Maven project, release, commit release, bump to next version, commit next version.
# The last (in a dotted version scheme) or only version number will be incremeted by one.
set -e

test "$GITHUB_TOKEN" || { echo "Error: env var GITHUB_TOKEN not set"; exit 1; }
test "$GITHUB_ACTOR" || { echo "Error: env var GITHUB_ACTOR not set"; exit 1; }
test "$GITHUB_REPOSITORY" || { echo "Error: env var GITHUB_REPOSITORY not set"; exit 1; }

PROJECT_VERSION=$(mvn -B -q help:evaluate -Dexpression=project.version -DforceStdout=true)
RELEASE_VERSION=${PROJECT_VERSION%-SNAPSHOT}
if [ "${PROJECT_VERSION}" = "${RELEASE_VERSION}" ]; then
    echo >&2 "Expected a SNAPSHOT version in POM, will not continue with automatic release."
    exit 1
fi

if [ "${RELEASE_VERSION//./}" != "$RELEASE_VERSION" ]; then
    NEXT_SNAPSHOT_VERSION="${RELEASE_VERSION%.*}.$((${RELEASE_VERSION##*.} + 1))-SNAPSHOT"
else
    NEXT_SNAPSHOT_VERSION="$((RELEASE_VERSION + 1))-SNAPSHOT"
fi

echo "-- Setting release version $RELEASE_VERSION .."
mvn -B versions:set -DnewVersion="$RELEASE_VERSION" -DgenerateBackupPoms=false

echo "-- Build, test and deploy release .."
mvn -B --settings maven-settings.xml clean deploy -Dmaven.wagon.http.pool=false


echo "-- Commit and tag release version .."
git config user.email github-action@users.noreply.github.com
git config user.name 'GitHub Action'
git remote set-url origin "https://${GITHUB_ACTOR}:${GITHUB_TOKEN}@github.com/${GITHUB_REPOSITORY}.git"
git checkout master

git commit -am "Set project version to $RELEASE_VERSION for release"
git push -q origin
git tag -f -a -m "Release $RELEASE_VERSION" "$RELEASE_VERSION"
git push -q --tags origin

echo "-- Bump to next development version $NEXT_SNAPSHOT_VERSION .."
mvn -B versions:set -DnewVersion="$NEXT_SNAPSHOT_VERSION" -DgenerateBackupPoms=false

echo "-- Commit next development version .."
git commit -am "Set next project version to $NEXT_SNAPSHOT_VERSION"
git push -q origin
