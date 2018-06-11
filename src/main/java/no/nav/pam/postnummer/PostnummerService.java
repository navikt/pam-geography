package no.nav.pam.postnummer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Source: https://www.bring.no/radgivning/sende-noe/adressetjenester/postnummer
 */
public class PostnummerService {

    private static final Logger LOG = LoggerFactory.getLogger(PostnummerService.class);
    private final static String FILENAME = "postnummerregister.txt";

    private final Map<String, PostData> postalCodeTable;

    public PostnummerService() {
        postalCodeTable = new HashMap<>();
    }

    @PostConstruct
    public PostnummerService init() throws Exception {

        String line;
        String csvSplitBy = "\t";

        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(FILENAME), UTF_8));
        while ((line = br.readLine()) != null) {
            String[] postArray = line.split(csvSplitBy);

            String countyName = Fylkesoversikt.finnFylke(postArray[2].substring(0, 2)).orElse(null);
            PostData data = new PostData(postArray[0], postArray[1], postArray[3], postArray[2], countyName);
            postalCodeTable.put(data.getPostalCode(), data);
        }

        LOG.info("Imported the postal code table from file to memory.");
        return this;

    }

    public Optional<PostData> findPostData(String postkode) {
        return Optional.ofNullable(postalCodeTable.get(postkode));
    }

    public static class PostData {

        private String postalCode;
        private String city;
        private String municipality;
        private String municipalityCode;
        private String county;

        public PostData(String postalCode, String city, String municipality, String municipalityCode, String county) {
            this.postalCode = postalCode;
            this.city = city;
            this.municipality = municipality;
            this.municipalityCode = municipalityCode;
            this.county = county;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public String getCity() {
            return city;
        }

        public String getMunicipality() {
            return municipality;
        }

        public String getMunicipalityCode() {
            return municipalityCode;
        }

        public String getCounty() {
            return county;
        }
    }
}
