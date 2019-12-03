package no.nav.pam.geography;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Source: https://www.bring.no/radgivning/sende-noe/adressetjenester/postnummer
 */
public class ArenaGeographyDAO {

    private final static String FILENAME = "postal_codes_no.tsv";

    private final CountryDAO countryDAO = new CountryDAO();
    private final Country NORWAY = countryDAO.findCountry("norge").orElseThrow(()->new RuntimeException("There was an error finding Norway"));

    private final Map<String, ArenaGeography> arenaCodeTable = new HashMap<>();

    public ArenaGeographyDAO() throws IOException {

        String line;
        final String csvSplitBy = "\t";

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILENAME)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF_8));
            while ((line = br.readLine()) != null) {
                String[] postArray = line.split(csvSplitBy);

                Municipality municipality = new Municipality(postArray[2], postArray[3]);
                County county = CountyDAO.findCounty(municipality.getCountyCode()).orElse(null);
                ArenaGeography codeWithMunicipality = new ArenaGeography(NORWAY, county, municipality);
                ArenaGeography codeWithCounty = new ArenaGeography(NORWAY, county);

                if (municipality == null || county == null) {
                    throw new RuntimeException("There was an error parsing arena codes from file for postal code");
                }

                arenaCodeTable.put(codeWithMunicipality.getCode(), codeWithMunicipality);
                arenaCodeTable.put(codeWithCounty.getCode(), codeWithCounty);

            }
            ArenaGeography codeWithCountry = new ArenaGeography(NORWAY);
            arenaCodeTable.put(codeWithCountry.getCode(), codeWithCountry);
        }
    }

    public Optional<ArenaGeography> findArenaGeography(String arenaCode) {
        return Optional.ofNullable(arenaCodeTable.get(arenaCode));
    }

    public Set<ArenaGeography> getAllArenaGeographies() {
        return Collections.unmodifiableSet(new HashSet<>(arenaCodeTable.values()));
    }
}
