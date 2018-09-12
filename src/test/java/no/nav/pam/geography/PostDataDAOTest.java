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
        assertEquals("0301", pd1.getMunicipality().get().getCode());
        assertEquals("OSLO", pd1.getMunicipality().get().getName());
        assertEquals("OSLO", pd1.getCounty().get().getName());

        PostData pd2 = service.findPostData("7053").orElse(null);
        assertEquals("RANHEIM", pd2.getCity());
        assertEquals("5001", pd2.getMunicipality().get().getCode());
        assertEquals("TRONDHEIM", pd2.getMunicipality().get().getName());
        assertEquals("TRØNDELAG", pd2.getCounty().get().getName());
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
        assertEquals(429, municipalitySet.size());
    }
}
