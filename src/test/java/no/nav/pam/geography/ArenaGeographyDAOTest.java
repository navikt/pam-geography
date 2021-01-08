package no.nav.pam.geography;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ArenaGeographyDAOTest {

    private final ArenaGeographyDAO service;

    public ArenaGeographyDAOTest() throws IOException {
        this.service = new ArenaGeographyDAO(new CountryDAO(), new PostDataDAO());
    }

    @Test
    public void should_lookup_arenageography() {
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
        assertEquals(373, service.getAllArenaGeographies().stream().filter(geo -> !geo.getCode().startsWith("NO99")).count());
    }

    @Test
    public void should_have_NO99_geographies() {
        assertEquals("ØVRIGE OMRÅDER", service.findArenaGeography("NO99").map(ArenaGeography::getName).orElse(null));
        assertEquals("SVALBARD", service.findArenaGeography("NO99.2100").map(ArenaGeography::getName).orElse(null));
    }
}
