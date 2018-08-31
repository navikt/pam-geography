package no.nav.pam.geography;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;
import java.util.Optional;


public class CountryServiceTest {


    @Test
    public void should_read_file_correctly() throws Exception {

        CountryService service = new CountryService().init();
        List<Country> list = service.getImmutableCountryList();

        Optional<Country> norway = list.stream().filter(s -> s.getCode().equals("NO")).findFirst();
        TestCase.assertTrue(norway.isPresent());
        TestCase.assertEquals("Norge", norway.get().getName());

        Optional<Country> header = list.stream().filter(s -> s.getCode().equals("code")).findFirst();
        TestCase.assertFalse(header.isPresent());
    }
}
