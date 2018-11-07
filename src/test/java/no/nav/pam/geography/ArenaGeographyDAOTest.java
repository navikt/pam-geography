package no.nav.pam.geography;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Test;

public class ArenaGeographyDAOTest {

    @Test
    public void should_lookup_arenageography() throws Exception {
        ArenaGeographyDAO service = new ArenaGeographyDAO();

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

        ag = service.findArenaGeography("NO02").orElse(null);
        assertEquals("NO02", ag.getCode());
        assertEquals("AKERSHUS", ag.getName());

        ag = service.findArenaGeography("NO02.0220").orElse(null);
        assertEquals("NO02.0220", ag.getCode());
        assertEquals("ASKER", ag.getName());
    }


    @Test
    public void should_get_all_arenageographies() throws IOException {
        ArenaGeographyDAO service = new ArenaGeographyDAO();

        Set<ArenaGeography> arenaGeographySet = service.getAllArenaGeographies();
        assertFalse(arenaGeographySet.isEmpty());
        assertEquals(450, arenaGeographySet.size());
    }
}
