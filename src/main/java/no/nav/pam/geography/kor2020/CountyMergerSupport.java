package no.nav.pam.geography.kor2020;

import no.nav.pam.geography.County;
import no.nav.pam.geography.CountyDAO;

import java.util.*;

public class CountyMergerSupport {

    private static final Set<County> COUNTY_SET_2019;
    private static final Map<String, String> TRANSITIONS;

    static {
        COUNTY_SET_2019 = new HashSet();
        COUNTY_SET_2019.add(new County("01", "ØSTFOLD"));
        COUNTY_SET_2019.add(new County("02", "AKERSHUS"));
        COUNTY_SET_2019.add(new County("03", "OSLO"));
        COUNTY_SET_2019.add(new County("04", "HEDMARK"));
        COUNTY_SET_2019.add(new County("05", "OPPLAND"));
        COUNTY_SET_2019.add(new County("06", "BUSKERUD"));
        COUNTY_SET_2019.add(new County("07", "VESTFOLD"));
        COUNTY_SET_2019.add(new County("08", "TELEMARK"));
        COUNTY_SET_2019.add(new County("09", "AUST-AGDER"));
        COUNTY_SET_2019.add(new County("10", "VEST-AGDER"));
        COUNTY_SET_2019.add(new County("11", "ROGALAND"));
        COUNTY_SET_2019.add(new County("12", "HORDALAND"));
        COUNTY_SET_2019.add(new County("14", "SOGN OG FJORDANE"));
        COUNTY_SET_2019.add(new County("15", "MØRE OG ROMSDAL"));
        COUNTY_SET_2019.add(new County("18", "NORDLAND"));
        COUNTY_SET_2019.add(new County("19", "TROMS"));
        COUNTY_SET_2019.add(new County("20", "FINNMARK"));
        COUNTY_SET_2019.add(new County("50", "TRØNDELAG"));
        COUNTY_SET_2019.add(new County("21", "SVALBARD"));
        COUNTY_SET_2019.add(new County("22", "JAN MAYEN"));
        COUNTY_SET_2019.add(new County("23", "KONTINENTALSOKKELEN"));

        TRANSITIONS = new HashMap<>();
        // Viken
        TRANSITIONS.put("01", "30");
        TRANSITIONS.put("02", "30");
        TRANSITIONS.put("06", "30");
        // Innlandet
        TRANSITIONS.put("04", "34");
        TRANSITIONS.put("05", "34");
        // Vestfold og Telemark
        TRANSITIONS.put("07", "38");
        TRANSITIONS.put("08", "38");
        // Agder
        TRANSITIONS.put("09", "42");
        TRANSITIONS.put("10", "42");
        // Vestland
        TRANSITIONS.put("12", "46");
        TRANSITIONS.put("14", "46");
        // Troms og Finnmark
        TRANSITIONS.put("19", "54");
        TRANSITIONS.put("20", "54");
    }

    /**
     * Lookup county by code.
     *
     * <p>If the code belongs to a pre-merger county, and corresponding new county will be returned.
     * If the code belongs to a current county, it will be returned as usual, like {@link CountyDAO#findCounty(String)}
     * </p>
     *
     * @param oldOrNewCode county code, old or new
     * @return a transitioned or existing county if either old or new code is known, otherwise empty optional
     */
    public Optional<County> findByCode(String oldOrNewCode) {
        String transitionedCode = TRANSITIONS.get(oldOrNewCode);
        return transitionedCode != null ?
                CountyDAO.findCounty(transitionedCode) : CountyDAO.findCounty(oldOrNewCode);
    }

    /**
     * @return set of old counties pre-2020-merger
     */
    public Set<County> getOldCountySet() {
        return Collections.unmodifiableSet(COUNTY_SET_2019);
    }

}
