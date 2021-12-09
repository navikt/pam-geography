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

        getNO99Geographies().forEach(arenaGeography -> arenaCodeTable.put(arenaGeography.getCode(), arenaGeography));
    }

    public Optional<ArenaGeography> findArenaGeography(String arenaCode) {
        return Optional.ofNullable(arenaCodeTable.get(arenaCode));
    }

    public Collection<ArenaGeography> getAllArenaGeographies() {
        return Collections.unmodifiableCollection(arenaCodeTable.values());
    }

    /**
     * NO99.* (with county codes 21, 22 and 23), or territorial geographies, are present in Janzz ontology, so we should recognize
     * them with the same prefix in this library as well, for consistency.
     *
     * <p>Source: https://github.com/navikt/pam-cv-api/blob/2c2149c46b59c9388cd00ece1ef4b8aefafee8f7/app/src/main/resources/location_2020.json</p>
     *
     * @return list of arena geographies of territories
     */
    private static List<ArenaGeography> getNO99Geographies() {
        final Country norway = new Country("NO", "NOR", "NORGE");
        final County territories = new County("99", "ØVRIGE OMRÅDER");

        return Arrays.asList(new ArenaGeography(norway, territories),
            new ArenaGeography(norway, territories, new Municipality("2131", "HOPEN")),
            new ArenaGeography(norway, territories, new Municipality("2121", "BJØRNØYA")),
            new ArenaGeography(norway, territories, new Municipality("2100", "SVALBARD")),
            new ArenaGeography(norway, territories, new Municipality("2211", "JAN MAYEN")),
            new ArenaGeography(norway, territories, new Municipality("2201", "NORDSJØEN")),
            new ArenaGeography(norway, territories, new Municipality("2111", "LONGYEARBYEN")),
            new ArenaGeography(norway, territories, new Municipality("2311", "SOKKELEN SØR FOR 62 BRGR")),
            new ArenaGeography(norway, territories, new Municipality("2321", "SOKKELEN NORD FOR 62 BRGR")));
    }
}
