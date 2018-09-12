package no.nav.pam.geography;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Source: https://www.bring.no/radgivning/sende-noe/adressetjenester/postnummer/postnummertabeller-veiledning
 */
public class CountyDAO {

    private static final Set<County> COUNTY_SET;

    static {
        COUNTY_SET = new HashSet();
        COUNTY_SET.add(new County("01", "ØSTFOLD"));
        COUNTY_SET.add(new County("02", "AKERSHUS"));
        COUNTY_SET.add(new County("03", "OSLO"));
        COUNTY_SET.add(new County("04", "HEDMARK"));
        COUNTY_SET.add(new County("05", "OPPLAND"));
        COUNTY_SET.add(new County("06", "BUSKERUD"));
        COUNTY_SET.add(new County("07", "VESTFOLD"));
        COUNTY_SET.add(new County("08", "TELEMARK"));
        COUNTY_SET.add(new County("09", "AUST-AGDER"));
        COUNTY_SET.add(new County("10", "VEST-AGDER"));
        COUNTY_SET.add(new County("11", "ROGALAND"));
        COUNTY_SET.add(new County("12", "HORDALAND"));
        COUNTY_SET.add(new County("14", "SOGN OG FJORDANE"));
        COUNTY_SET.add(new County("15", "MØRE OG ROMSDAL"));
        COUNTY_SET.add(new County("18", "NORDLAND"));
        COUNTY_SET.add(new County("19", "TROMS"));
        COUNTY_SET.add(new County("20", "FINNMARK"));
        COUNTY_SET.add(new County("50", "TRØNDELAG"));
        COUNTY_SET.add(new County("21", "SVALBARD"));
        COUNTY_SET.add(new County("22", "JAN MAYEN"));
        COUNTY_SET.add(new County("23", "KONTINENTALSOKKELEN"));
    }

    public static Optional<County> findCounty(String countyNumber) {
        return COUNTY_SET.stream().filter(c -> c.getCode().equals(countyNumber)).findAny();
    }

    public static Optional<String> findCountyName(String countyNumber) {
        return COUNTY_SET.stream().filter(c -> c.getCode().equals(countyNumber)).map(c -> c.getName()).findAny();
    }

    public static Set<County> getImmutableCountySet() {
        return Collections.unmodifiableSet(COUNTY_SET);
    }
}
