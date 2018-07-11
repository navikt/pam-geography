package no.nav.pam.postnummer;

import
        no.nav.pam.postnummer.PostnummerService.PostData;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PostnummerServiceTest {

    @Test
    public void should_lookup_postcode() throws Exception {
        PostnummerService service = new PostnummerService();
        service.init();

        PostData pd1 = service.findPostData("0010").orElse(null);
        assertEquals("OSLO", pd1.getCity());
        assertEquals("0301", pd1.getMunicipalityCode());
        assertEquals("OSLO", pd1.getMunicipality());
        assertEquals("OSLO", pd1.getCounty());

        PostData pd2 = service.findPostData("7053").orElse(null);
        assertEquals("RANHEIM", pd2.getCity());
        assertEquals("5001", pd2.getMunicipalityCode());
        assertEquals("TRONDHEIM", pd2.getMunicipality());
        assertEquals("TRØNDELAG", pd2.getCounty());
    }

    @Test
    public void should_get_all_postdata_as_list() throws Exception {
        PostnummerService postnummerService = new PostnummerService();
        postnummerService.init();

        List<PostData> postDataList = postnummerService.findAllPostData();
        assertFalse(postDataList.isEmpty());
    }
}
