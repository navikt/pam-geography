package no.nav.pam.postnummer;


import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class LandServiceTest {


    @Test
    public void should_read_file_correctly() throws Exception {

        LandService service = new LandService().init();
        List<LandService.Land> landList = service.getLandListe();

        Optional<LandService.Land> norge = landList.stream().filter(s -> s.getIso2().equals("NO")).findFirst();
        assertTrue(norge.isPresent());
        assertEquals("Norge", norge.get().getName());
    }
}
