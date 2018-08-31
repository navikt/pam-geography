package no.nav.pam.geography;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Source: https://www.bring.no/radgivning/sende-noe/adressetjenester/postnummer
 */
public class PostalCodeService {

    private static final Logger LOG = LoggerFactory.getLogger(PostalCodeService.class);
    private final static String FILENAME = "postal_codes_no.tsv";

    private final Map<String, PostData> postalCodeTable;

    public PostalCodeService() {
        postalCodeTable = new HashMap<>();
    }

    @PostConstruct
    public PostalCodeService init() throws Exception {

        String line;
        String csvSplitBy = "\t";

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILENAME)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF_8));
            while ((line = br.readLine()) != null) {
                String[] postArray = line.split(csvSplitBy);

                String countyName = CountyService.findCounty(postArray[2].substring(0, 2)).orElse(null);
                PostData data = new PostData(postArray[0], postArray[1], postArray[3], postArray[2], countyName);
                postalCodeTable.put(data.getPostalCode(), data);
            }
        }

        LOG.info("Imported the postal code table from file to memory.");
        return this;

    }

    public Optional<PostData> findPostData(String postalCode) {
        return Optional.ofNullable(postalCodeTable.get(postalCode));
    }

    public List<PostData> findAllPostData() {
        return new ArrayList<>(postalCodeTable.values());
    }
}
