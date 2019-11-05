package no.nav.pam.geography;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MunicipalityDAOTest {

    @Test
    public void should_lookup_municipality() throws Exception {
        MunicipalityDAO service = new MunicipalityDAO();

        Municipality m1 = service.findMunicipality("3025").orElse(null);
        assertEquals("3025", m1.getCode());
        assertEquals("ASKER", m1.getName());

        Municipality m2 = service.findMunicipality("5001").orElse(null);
        assertEquals("5001", m2.getCode());
        assertEquals("TRONDHEIM", m2.getName());
    }

    @Test
    public void should_get_all_municipalities() throws IOException {
        MunicipalityDAO service = new MunicipalityDAO();
        Set<Municipality> municipalitySet = service.getAllMunicipalities();
        // Official number after 1.1.2020 is 356, and this library also includes Svalbard and Jan Mayen, which gives total of 358.
        assertEquals(358, municipalitySet.size());
    }
}
