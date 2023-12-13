package no.nav.pam.geography;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class MunicipalityDAOTest {

    @Test
    public void should_lookup_municipality() throws Exception {
        MunicipalityDAO service = new MunicipalityDAO();

        Municipality m1 = service.findMunicipality("3203").orElse(null);
        assertEquals("3203", m1.getCode());
        assertEquals("ASKER", m1.getName());

        Municipality m2 = service.findMunicipality("5001").orElse(null);
        assertEquals("5001", m2.getCode());
        assertEquals("TRONDHEIM", m2.getName());

        Municipality m3 = service.findMunicipality("1832").orElse(null);
        assertEquals("1832", m3.getCode());
        assertEquals("HEMNES", m3.getName());
    }

    @Test
    public void should_get_all_municipalities() throws IOException {
        MunicipalityDAO service = new MunicipalityDAO();
        Set<Municipality> municipalitySet = service.getAllMunicipalities();
        // Official number after 1.1.2020 is 356, and this library also includes Svalbard and Jan Mayen, which gives total of 358.
        assertEquals(359, municipalitySet.size());
    }

    // Bug observed in data file from Norwegian postal services where a small percentage of municipality names contained
    // a non-breaking whitespace character at the end of the name. Check that no such instances exist.
    @Test
    public void no_municipality_names_should_end_with_nonbreakspace_char() throws IOException {
        new MunicipalityDAO().getAllMunicipalities().forEach(m -> {
            assertFalse(m.getName().charAt(m.getName().length()-1) == '\u00A0');
        });
    }
}
