package no.nav.pam.geography;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CountryDAO {

    private final static String FILENAME = "country_codes_iso3166.csv";

    private final List<Country> countryList;

    public CountryDAO() throws IOException {
        List<Country> templist = new ArrayList();

        String line;
        String csvSplitBy = ";";

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILENAME)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF_8));
            while ((line = br.readLine()) != null) {
                String[] landArray = line.split(csvSplitBy);

                Country country = new Country(stripQuotesAndUppercase(landArray[0]), stripQuotesAndUppercase(landArray[3]));
                templist.add(country);
            }
        }

        //remove headers
        templist.remove(0);
        countryList = Collections.unmodifiableList(templist);
    }

    public List<Country> getImmutableCountryList() {
        return countryList;
    }

    public Optional<Country> findCountry(String name) {

        return countryList.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<Country> findCountryByCode(String code) {
        return countryList.stream().filter(c -> c.getCode().equalsIgnoreCase(code)).findFirst();
    }


    private String stripQuotesAndUppercase(String value) {
        return value.substring(1, value.length() - 1).toUpperCase();
    }
}
