package no.nav.pam.geography;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


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
        // Official number after 1.1.2020 is 356, and this library also includes Svalbard and Jan Mayen, which gives total of 358.
        assertEquals(358, municipalitySet.size());
    }

    @Test
    public void no_city_names_should_end_with_nonbreakwhitespace_char() throws IOException {
        new PostDataDAO().getAllPostData().forEach(p -> {
            assertFalse(p.getCity().charAt(p.getCity().length()-1) == '\u00A0');
        });
    }

}
