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
        COUNTY_SET = new HashSet<>();
        COUNTY_SET.add(new County("03", "OSLO"));
        COUNTY_SET.add(new County("11", "ROGALAND"));
        COUNTY_SET.add(new County("15", "MØRE OG ROMSDAL"));
        COUNTY_SET.add(new County("18", "NORDLAND"));
        COUNTY_SET.add(new County("21", "SVALBARD"));
        COUNTY_SET.add(new County("22", "JAN MAYEN"));
        COUNTY_SET.add(new County("23", "KONTINENTALSOKKELEN"));
        COUNTY_SET.add(new County("30", "VIKEN"));
        COUNTY_SET.add(new County("34", "INNLANDET"));
        COUNTY_SET.add(new County("38", "VESTFOLD OG TELEMARK"));
        COUNTY_SET.add(new County("42", "AGDER"));
        COUNTY_SET.add(new County("46", "VESTLAND"));
        COUNTY_SET.add(new County("50", "TRØNDELAG"));
        COUNTY_SET.add(new County("54", "TROMS OG FINNMARK"));
    }

    public static Optional<County> findCounty(String countyNumber) {
        return COUNTY_SET.stream().filter(c -> c.getCode().equals(countyNumber)).findAny();
    }

    public static Optional<String> findCountyName(String countyNumber) {
        return findCounty(countyNumber).map(c -> c.getName());
    }

    public static Set<County> getImmutableCountySet() {
        return Collections.unmodifiableSet(COUNTY_SET);
    }

}
