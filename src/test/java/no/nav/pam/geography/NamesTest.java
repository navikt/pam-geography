package no.nav.pam.geography;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NamesTest {

    @Test
    public void testCapitalizeLocationName() {
        assertEquals("Oslo", Names.capitalizeLocationName("OSLO"));
        assertEquals("Sør-Fron", Names.capitalizeLocationName("SØR-FRON"));
        assertEquals("Bø i Telemark", Names.capitalizeLocationName("BØ I TELEMARK"));
        assertEquals("Troms og Finnmark", Names.capitalizeLocationName("TROMS OG FINNMARK"));
        assertEquals("Sør-Varanger", Names.capitalizeLocationName("SØR-VARANGER"));
        assertEquals("Østre Toten", Names.capitalizeLocationName("ØSTRE TOTEN"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("", Names.capitalizeLocationName(null));
        assertEquals("", Names.capitalizeLocationName(""));
        assertEquals("", Names.capitalizeLocationName("            "));
        assertEquals("", Names.capitalizeLocationName("\t"));
        assertEquals(" Bodø", Names.capitalizeLocationName(" bodø"));
    }
}
