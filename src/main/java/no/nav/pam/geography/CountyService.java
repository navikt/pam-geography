package no.nav.pam.geography;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Source: https://www.bring.no/radgivning/sende-noe/adressetjenester/postnummer/postnummertabeller-veiledning
 */
public class CountyService {

    private static final Map<String, String> COUNTY_MAP;

    static {
        COUNTY_MAP = new HashMap<>();
        COUNTY_MAP.put("01", "ØSTFOLD");
        COUNTY_MAP.put("02", "AKERSHUS");
        COUNTY_MAP.put("03", "OSLO");
        COUNTY_MAP.put("04", "HEDMARK");
        COUNTY_MAP.put("05", "OPPLAND");
        COUNTY_MAP.put("06", "BUSKERUD");
        COUNTY_MAP.put("07", "VESTFOLD");
        COUNTY_MAP.put("08", "TELEMARK");
        COUNTY_MAP.put("09", "AUST-AGDER");
        COUNTY_MAP.put("10", "VEST-AGDER");
        COUNTY_MAP.put("11", "ROGALAND");
        COUNTY_MAP.put("12", "HORDALAND");
        COUNTY_MAP.put("14", "SOGN OG FJORDANE");
        COUNTY_MAP.put("15", "MØRE OG ROMSDAL");
        COUNTY_MAP.put("18", "NORDLAND");
        COUNTY_MAP.put("19", "TROMS");
        COUNTY_MAP.put("20", "FINNMARK");
        COUNTY_MAP.put("50", "TRØNDELAG");
        COUNTY_MAP.put("21", "SVALBARD");
        COUNTY_MAP.put("22", "JAN MAYEN");
        COUNTY_MAP.put("23", "KONTINENTALSOKKELEN");
    }

    public static Map<String, String> getImmutableCountyMap() {
        return Collections.unmodifiableMap(COUNTY_MAP);
    }

    public static Optional<String> findCounty(String countyNumber) {
        return Optional.ofNullable(COUNTY_MAP.get(countyNumber));
    }
}
