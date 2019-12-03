package no.nav.pam.geography.kor2020;

import no.nav.pam.geography.MunicipalityDAO;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MunicipalityMergerSupportTest {

    private final MunicipalityMergerSupport mergerSupport;

    public MunicipalityMergerSupportTest() throws IOException {
        this.mergerSupport = new MunicipalityMergerSupport(new MunicipalityDAO());
    }

    @Test
    public void testMunicipalityMergers() throws IOException {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(MunicipalityMergerSupport.MERGERS_RESOURCE), StandardCharsets.UTF_8))) {
            r.lines().filter(line -> !line.startsWith("#"))
                    .map(line -> line.split("\t"))
                    .map(parts -> parts[1].split(" ")[0])
                    .forEach(oldCode -> assertTrue(mergerSupport.findByCode(oldCode).isPresent()));
        }
    }

    // In case a municipality has been split instead of merged (a few cases), the first split-out municipality should
    // be selected, based on order in data source. (Solving ambiguity with a least a consistency rule.)
    @Test
    public void testSelectFirstIfMunicipalitySplit() {
        assertEquals("HEIM", mergerSupport.findByCode("5012").map(m -> m.getName()).orElse(null));
    }

    @Test
    public void testSomeCases() {
        assertEquals("5434", mergerSupport.findByCode("2018").map(m -> m.getCode()).orElse(null));
        assertEquals("5434", mergerSupport.findByCode("5434").map(m -> m.getCode()).orElse(null));
        assertEquals("MÅSØY", mergerSupport.findByCode("5434").map(m -> m.getName()).orElse(null));

        assertEquals("INDRE ØSTFOLD", mergerSupport.findByCode("0124").map(m -> m.getName()).orElse(null));

        assertEquals("OSLO", mergerSupport.findByCode("0301").map(m -> m.getName()).orElse(null));
    }
}


