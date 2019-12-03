package no.nav.pam.geography.kor2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountyMergerSupportTest {

    @Test
    public void testLookupOldCode() {
        assertEquals("VIKEN",
                new CountyMergerSupport().findByCode("01").map(c -> c.getName()).orElse(null));
        assertEquals("VIKEN",
                new CountyMergerSupport().findByCode("02").map(c -> c.getName()).orElse(null));
        assertEquals("TROMS OG FINNMARK",
                new CountyMergerSupport().findByCode("19").map(c -> c.getName()).orElse(null));
    }

    @Test
    public void testLookupNewCode() {
        assertEquals("VIKEN",
                new CountyMergerSupport().findByCode("30").map(c -> c.getName()).orElse(null));
    }

    @Test
    public void testLookupExistingCode() {
        assertEquals("OSLO",
                new CountyMergerSupport().findByCode("03").map(c -> c.getName()).orElse(null));
    }

}
