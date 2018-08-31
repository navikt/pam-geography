package no.nav.pam.geography;

public class PostData {

    private String postalCode;
    private String city;
    private String municipality;
    private String municipalityCode;
    private String county;

    public PostData(String postalCode, String city, String municipality, String municipalityCode, String county) {
        this.postalCode = postalCode;
        this.city = city;
        this.municipality = municipality;
        this.municipalityCode = municipalityCode;
        this.county = county;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public String getCounty() {
        return county;
    }
}