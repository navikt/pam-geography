package no.nav.pam.geography;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ArenaGeographyDAOTest {

    private final PostDataDAO postDataDAO;
    private final CountryDAO countryDAO;

    public ArenaGeographyDAOTest() throws IOException {
        this.postDataDAO = new PostDataDAO();
        this.countryDAO = new CountryDAO();
    }

    @Test
    public void should_lookup_arenageography() {
        ArenaGeographyDAO service = new ArenaGeographyDAO(countryDAO, postDataDAO);

        ArenaGeography ag = service.findArenaGeography("NO").orElse(null);
        assertEquals("NO", ag.getCode());
        assertEquals("NORGE", ag.getName());

        ag = service.findArenaGeography("NO03").orElse(null);
        assertEquals("NO03", ag.getCode());
        assertEquals("OSLO", ag.getName());

        ag = service.findArenaGeography("NO03.0301").orElse(null);
        assertEquals("NO03.0301", ag.getCode());
        assertEquals("OSLO", ag.getName());

        ag = service.findArenaGeography("NO50").orElse(null);
        assertEquals("NO50", ag.getCode());
        assertEquals("TRØNDELAG", ag.getName());

        ag = service.findArenaGeography("NO50.5001").orElse(null);
        assertEquals("NO50.5001", ag.getCode());
        assertEquals("TRONDHEIM", ag.getName());

        ag = service.findArenaGeography("NO30").orElse(null);
        assertEquals("NO30", ag.getCode());
        assertEquals("VIKEN", ag.getName());

        ag = service.findArenaGeography("NO30.3025").orElse(null);
        assertEquals("NO30.3025", ag.getCode());
        assertEquals("ASKER", ag.getName());
    }


    @Test
    public void should_get_all_arenageographies() {
        ArenaGeographyDAO service = new ArenaGeographyDAO(countryDAO, postDataDAO);

        Collection<ArenaGeography> arenaGeographies = service.getAllArenaGeographies();
        assertEquals(373, arenaGeographies.size());
    }
}
