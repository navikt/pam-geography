package no.nav.pam.geography;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Source: https://www.bring.no/radgivning/sende-noe/adressetjenester/postnummer
 */
public class ArenaGeographyDAO {

    private static final Logger LOG = LoggerFactory.getLogger(ArenaGeographyDAO.class);
    private final static String FILENAME = "postal_codes_no.tsv";

    private final CountryDAO countryDAO = new CountryDAO();
    private final Country NORWAY = countryDAO.findCountry("norge").orElseThrow(()->new RuntimeException("There was an error finding Norway"));

    private final Map<String, ArenaGeography> arenaCodeTable = new HashMap<>();

    public ArenaGeographyDAO() throws IOException {

        String line;
        String csvSplitBy = "\t";

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILENAME)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF_8));
            while ((line = br.readLine()) != null) {
                String[] postArray = line.split(csvSplitBy);

                Municipality municipality = new Municipality(postArray[2], postArray[3]);
                County county = CountyDAO.findCounty(postArray[2].substring(0, 2)).orElse(null);
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

        LOG.debug("Imported the postal code table from file to memory for arena codes.");
    }

    public Optional<ArenaGeography> findArenaGeography(String arenaCode) {
        return Optional.ofNullable(arenaCodeTable.get(arenaCode));
    }

    public Set<ArenaGeography> getAllArenaGeographies() {
        return Collections.unmodifiableSet(new HashSet<>(arenaCodeTable.values()));
    }
}
