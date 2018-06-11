package no.nav.pam.postnummer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostnummerServiceTest {

    @Test
    public void should_lookup_postcode() throws Exception {
        PostnummerService service = new PostnummerService();
        service.init();

        PostnummerService.PostData pd1 = service.findPostData("0010").orElse(null);
        assertEquals("OSLO", pd1.getCity());
        assertEquals("0301", pd1.getMunicipalityCode());
        assertEquals("OSLO", pd1.getMunicipality());
        assertEquals("OSLO", pd1.getCounty());

        PostnummerService.PostData pd2 = service.findPostData("7053").orElse(null);
        assertEquals("RANHEIM", pd2.getCity());
        assertEquals("5001", pd2.getMunicipalityCode());
        assertEquals("TRONDHEIM", pd2.getMunicipality());
        assertEquals("TRØNDELAG", pd2.getCounty());
    }
}
