package no.nav.pam.geography;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class MunicipalityDAOTest {

    @Test
    public void should_lookup_municipality() throws Exception {
        MunicipalityDAO service = new MunicipalityDAO();

        Municipality m1 = service.findMunicipality("0220").orElse(null);
        assertEquals("0220", m1.getCode());
        assertEquals("ASKER", m1.getName());

        Municipality m2 = service.findMunicipality("5001").orElse(null);
        assertEquals("5001", m2.getCode());
        assertEquals("TRONDHEIM", m2.getName());
    }

    @Test
    public void should_get_all_municipalities() throws IOException {
        MunicipalityDAO service = new MunicipalityDAO();
        Set<Municipality> municipalitySet = service.getAllMunicipalities();
        assertFalse(municipalitySet.isEmpty());
        assertEquals(429, municipalitySet.size());
    }
}
