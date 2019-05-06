package no.nav.pam.geography;


import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PostDataDAOTest {

    @Test
    public void should_lookup_postcode() throws Exception {
        PostDataDAO service = new PostDataDAO();

        PostData pd1 = service.findPostData("0010").orElse(null);
        assertEquals("OSLO", pd1.getCity());
        assertEquals("0301", pd1.getMunicipality().getCode());
        assertEquals("OSLO", pd1.getMunicipality().getName());
        assertEquals("OSLO", pd1.getCounty().getName());

        PostData pd2 = service.findPostData("7053").orElse(null);
        assertEquals("RANHEIM", pd2.getCity());
        assertEquals("5001", pd2.getMunicipality().getCode());
        assertEquals("TRONDHEIM", pd2.getMunicipality().getName());
        assertEquals("TRØNDELAG", pd2.getCounty().getName());
    }

    @Test
    public void should_get_all_postdata_as_list() throws Exception {
        PostDataDAO service = new PostDataDAO();
        List<PostData> postDataList = service.getAllPostData();
        assertFalse(postDataList.isEmpty());
    }

    @Test
    public void should_get_all_municipalities() throws IOException {
        PostDataDAO service = new PostDataDAO();
        Set<Municipality> municipalitySet = service.getAllMunicipalities();
        assertFalse(municipalitySet.isEmpty());
        assertEquals(424, municipalitySet.size());
    }
}
