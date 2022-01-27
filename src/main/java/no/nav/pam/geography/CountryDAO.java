package no.nav.pam.geography;


import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.exceptions.CsvValidationException;
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

    private final static String FILENAME = "world.csv";

    private final List<Country> countryList;

    public CountryDAO() throws IOException {
        List<Country> templist = new ArrayList<>();

        String[] line;

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILENAME)) {
            CSVReaderHeaderAware reader = new CSVReaderHeaderAwareBuilder(new BufferedReader(new InputStreamReader(is, UTF_8)))
                .withCSVParser(
                    new CSVParserBuilder().withSeparator(',').build()
                )
                .build();

            while ((line = reader.readNext("alpha2", "alpha3", "name")) != null) {
                Country country = new Country(line[0].toUpperCase(), line[1].toUpperCase(), line[2].toUpperCase());
                templist.add(country);
            }
        } catch (CsvValidationException e) {
            throw new IOException(e);
        }
        countryList = Collections.unmodifiableList(templist);
    }

    public List<Country> getImmutableCountryList() {
        return countryList;
    }

    public Optional<Country> findCountry(String name) {

        return countryList.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<Country> findCountryByCode(String code) {
        return countryList.stream().filter(c -> c.getAlpha2Code().equalsIgnoreCase(code) || c.getAlpha3Code().equalsIgnoreCase(code))
            .findFirst();
    }
}
