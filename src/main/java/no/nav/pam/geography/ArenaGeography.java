package no.nav.pam.geography;

public class ArenaGeography {

    private String code;
    private String name;

    public ArenaGeography(Country country) {
        codeFromCountry(country);
    }

    public ArenaGeography(Country country, County county) {
        if (county == null) {
            codeFromCountry(country);
        } else {
            codeFromCountryAndCounty(country, county);
        }
    }

    public ArenaGeography(Country country, County county, Municipality municipality) {
        if (municipality == null) {
            if (county == null) {
                codeFromCountry(country);
            } else {
                codeFromCountryAndCounty(country, county);
            }
        } else {
            codeFromCountryCountyAndMunicipality(country, county, municipality);
        }
    }

    private void codeFromCountry(Country country) {
        this.code = country.getCode();
        this.name = country.getName();
    }

    private void codeFromCountryAndCounty(Country country, County county) {
        this.code = country.getCode() + county.getCode();
        this.name = county.getName();
    }

    private void codeFromCountryCountyAndMunicipality(Country country, County county, Municipality municipality) {
        this.code = country.getCode() + county.getCode() + "." + municipality.getCode();
        this.name = municipality.getName();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
