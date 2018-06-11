package no.nav.pam.postnummer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Source: https://www.kartverket.no/kunnskap/fakta-om-norge/Fylker-og-kommuner/Tabell/
 */
class Fylkesoversikt {

    private static final Map<String, String> FYLKE_MAP;

    static {
        FYLKE_MAP = new HashMap<>();
        FYLKE_MAP.put("01", "ØSTFOLD");
        FYLKE_MAP.put("02", "AKERSHUS");
        FYLKE_MAP.put("03", "OSLO");
        FYLKE_MAP.put("04", "HEDMARK");
        FYLKE_MAP.put("05", "OPPLAND");
        FYLKE_MAP.put("06", "BUSKERUD");
        FYLKE_MAP.put("07", "VESTFOLD");
        FYLKE_MAP.put("08", "TELEMARK");
        FYLKE_MAP.put("09", "AUST-AGDER");
        FYLKE_MAP.put("10", "VEST-AGDER");
        FYLKE_MAP.put("11", "ROGALAND");
        FYLKE_MAP.put("12", "HORDALAND");
        FYLKE_MAP.put("14", "SOGN OG FJORDANE");
        FYLKE_MAP.put("15", "MØRE OG ROMSDAL");
        FYLKE_MAP.put("50", "TRØNDELAG");
        FYLKE_MAP.put("18", "NORDLAND");
        FYLKE_MAP.put("19", "TROMS");
        FYLKE_MAP.put("20", "FINNMARK");
    }

    public static Optional<String> finnFylke(String fylkenummer) {
        return Optional.ofNullable(FYLKE_MAP.get(fylkenummer));
    }
}
