package no.nav.pam.geography;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class CountryDAOTest {

    @Test
    public void should_read_file_correctly() throws Exception {

        CountryDAO service = new CountryDAO();
        List<Country> list = service.getImmutableCountryList();

        Optional<Country> norway = list.stream().filter(s -> s.getCode().equals("NO")).findFirst();
        assertTrue(norway.isPresent());
        assertEquals("NORGE", norway.get().getName());

        Optional<Country> header = list.stream().filter(s -> s.getCode().equals("code")).findFirst();
        assertFalse(header.isPresent());
    }

    @Test
    public void should_find_country() throws IOException {
        CountryDAO service = new CountryDAO();
        assertTrue(service.findCountry("norge").isPresent());
        assertTrue(service.findCountry("sverige").isPresent());
        assertFalse(service.findCountry("sweden").isPresent());
    }

    @Test
    public void should_find_country_by_country_code() throws IOException {
        CountryDAO service = new CountryDAO();
        assertTrue(service.findCountryByCode("NO").isPresent());
        assertTrue(service.findCountryByCode("no").isPresent());
        assertEquals(service.findCountryByCode("NO"), service.findCountryByCode("NOR"));
        assertEquals(service.findCountryByCode("se"), service.findCountryByCode("swe"));
        assertFalse(service.findCountryByCode("xxx").isPresent());
    }
}
