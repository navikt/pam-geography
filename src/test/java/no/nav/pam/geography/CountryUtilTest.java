package no.nav.pam.geography;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class CountryUtilTest {


    @Test
    public void should_read_file_correctly() throws Exception {

        CountryUtil service = new CountryUtil();
        List<Country> list = service.getImmutableCountryList();

        Optional<Country> norway = list.stream().filter(s -> s.getCode().equals("NO")).findFirst();
        TestCase.assertTrue(norway.isPresent());
        TestCase.assertEquals("NORGE", norway.get().getName());

        Optional<Country> header = list.stream().filter(s -> s.getCode().equals("code")).findFirst();
        TestCase.assertFalse(header.isPresent());
    }

    @Test
    public void should_find_country() throws IOException {
        CountryUtil service = new CountryUtil();
        TestCase.assertTrue(service.findCountry("norge").isPresent());
        TestCase.assertTrue(service.findCountry("sverige").isPresent());
        TestCase.assertFalse(service.findCountry("sweden").isPresent());
    }
}