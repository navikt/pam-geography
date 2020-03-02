#!/bin/bash
set -e

TIME=$(TZ="Europe/Oslo" date +%Y%m%d)
COMMIT=$(git rev-parse --short=8 HEAD)
VERSION="1.$TIME-$COMMIT"
echo "Setting version $VERSION"

mvn -B versions:set -DnewVersion="$VERSION"
mvn -B versions:commit

echo "Running release"
mvn -B --settings maven-settings.xml deploy -Dmaven.wagon.http.pool=false
echo $VERSION > .version_tag