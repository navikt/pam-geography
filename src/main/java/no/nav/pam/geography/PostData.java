package no.nav.pam.geography;

public class PostData {

    private String postalCode;
    private String city;
    private Municipality municipality;
    private County county;

    public PostData(String postalCode, String city, Municipality municipality, County county) {
        this.postalCode = postalCode;
        this.city = city;
        this.municipality = municipality;
        this.county = county;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }
}