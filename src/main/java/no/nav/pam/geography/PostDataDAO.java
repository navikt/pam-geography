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

    // Overstyr navn pga etter kommunesammenslåingen er det mange som fremdeles tror de bruker den gamle kommunen, og
    // da havner stillingen feil sted geografisk. Ved å anngi fylke er det lettere å se at man velger riktig sted.
    private final Map<String, String> overriddenNamesMunicipality = new HashMap<>(){
            {
                put("3430", "OS (INNLANDET)");
                put("1514", "SANDE (MØRE OG ROMSDAL)");
                put("1867", "BØ (NORDLAND)");
                put("3228", "NES (AKERSHUS)");
            }
    };

    public PostDataDAO() throws IOException {

        postalCodeTable = new HashMap<>();

        String line;
        String csvSplitBy = "\t";

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILENAME)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF_8));
            while ((line = br.readLine()) != null) {
                String[] postArray = line.split(csvSplitBy);

                String municipalityCode = postArray[2];
                String municipalityName = postArray[3];
                Municipality municipality = new Municipality(municipalityCode, municipalityName);
                if (overriddenNamesMunicipality.containsKey(municipalityCode)) {
                    municipality = new Municipality(municipalityCode, overriddenNamesMunicipality.get(municipalityCode));
                }

                County county = CountyDAO.findCounty(municipality.getCountyCode())
                        .orElseThrow(() -> new IllegalStateException("No county found for code " + municipalityCode + ", update CountyDAO"));
                String postalCode = postArray[0];
                String city = postArray[1];
                if (county != null && postalCode != null && city !=null) {
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

    public List<PostData> findPostDataForCity(String city) {
        return postalCodeTable.values()
                .stream().filter(p -> p.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<PostData> postDataForCounty(String countyCode) {
        return postalCodeTable.values()
                .stream().filter(p -> p.getCounty().getCode().equalsIgnoreCase(countyCode))
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
