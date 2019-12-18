package no.nav.pam.geography;

import java.util.*;

/**
 * Sources/dependencies:
 * <ul>
 *     <li>{@link PostDataDAO}</li>
 *     <li>{@link CountryDAO}</li>
 * </ul>
 */
public class ArenaGeographyDAO {

    private final Map<String, ArenaGeography> arenaCodeTable = new HashMap<>();

    public ArenaGeographyDAO(CountryDAO countryDao, PostDataDAO postDataDAO) {

        final Country norway = countryDao.findCountry("norge").orElseThrow(
                () -> new IllegalStateException("Could not lookup 'norge' from CountryDAO"));

        ArenaGeography codeWithCountry = new ArenaGeography(norway);
        arenaCodeTable.put(codeWithCountry.getCode(), codeWithCountry);

        postDataDAO.getAllMunicipalities().forEach(municipality -> {
            County county = CountyDAO.findCounty(municipality.getCountyCode()).orElseThrow(
                    () -> new IllegalStateException(String.format("County for municipality {} not found in CountyDAO", municipality)));
            ArenaGeography codeWithMunicipality = new ArenaGeography(norway, county, municipality);
            arenaCodeTable.put(codeWithMunicipality.getCode(), codeWithMunicipality);
        });

        CountyDAO.getImmutableCountySet().forEach(county -> {
            ArenaGeography codeWithCounty = new ArenaGeography(norway, county);
            arenaCodeTable.put(codeWithCounty.getCode(), codeWithCounty);
        });
    }

    public Optional<ArenaGeography> findArenaGeography(String arenaCode) {
        return Optional.ofNullable(arenaCodeTable.get(arenaCode));
    }

    public Collection<ArenaGeography> getAllArenaGeographies() {
        return Collections.unmodifiableCollection(arenaCodeTable.values());
    }
}
