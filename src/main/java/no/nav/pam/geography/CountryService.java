package no.nav.pam.geography;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CountryService {

    private static final Logger LOG = LoggerFactory.getLogger(CountryService.class);
    private final static String FILENAME = "country_codes_iso3166.csv";

    private final List<Country> countryList;

    public CountryService() {
        countryList = new ArrayList();
    }

    public List<Country> getImmutableCountryList() {
        return Collections.unmodifiableList(countryList);
    }

    @PostConstruct
    public CountryService init() throws Exception {

        String line;
        String csvSplitBy = ";";

        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(FILENAME), UTF_8));
        while ((line = br.readLine()) != null) {
            String[] landArray = line.split(csvSplitBy);

            Country country = new Country(stripQuotes(landArray[0]), stripQuotes(landArray[3]));
            countryList.add(country);
        }

        //remove headers
        countryList.remove(0);

        LOG.info("Imported the postal code table from file to memory.");
        return this;
    }

    private String stripQuotes(String value) {
        return value.substring(1, value.length() - 1);
    }
}
