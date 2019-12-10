package no.nav.pam.geography;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Source: https://www.bring.no/radgivning/sende-noe/adressetjenester/postnummer
 */
public class PostDataDAO {

    private final static String FILENAME = "postal_codes_no.tsv";

    private final Map<String, PostData> postalCodeTable;

    public PostDataDAO() throws IOException {

        postalCodeTable = new HashMap<>();

        String line;
        String csvSplitBy = "\t";

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILENAME)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF_8));
            while ((line = br.readLine()) != null) {
                String[] postArray = line.split(csvSplitBy);

                Municipality municipality = new Municipality(postArray[2], postArray[3]);
                County county = CountyDAO.findCounty(municipality.getCountyCode())
                        .orElseThrow(() -> new IllegalStateException("No county found for code " + municipality.getCountyCode() + ", update CountyDAO"));
                String postalCode = postArray[0];
                String city = postArray[1];
                if (municipality != null && county != null && postalCode != null && city !=null) {
                    PostData data = new PostData(postalCode, city, municipality, county);
                    postalCodeTable.put(data.getPostalCode(), data);
                } else {
                    throw new IOException("There was an error parsing post data from file for postal code {}");
                }
            }
        }
    }

    public Optional<PostData> findPostData(String postalCode) {
        return Optional.ofNullable(postalCodeTable.get(postalCode));
    }

    public List<PostData> postDataForCounty(String countyCode) {
        return postalCodeTable.values()
                .stream().filter(p -> p.getCounty().getCode().equals(countyCode))
                .collect(Collectors.toList());
    }

    public List<PostData> getAllPostData() {
        return Collections.unmodifiableList(new ArrayList<>(postalCodeTable.values()));
    }

    public Set<Municipality> getAllMunicipalities() {
        return getAllPostData().stream()
                .map(p -> p.getMunicipality())
                .collect(Collectors.toSet());
    }
}
