package no.nav.pam.geography;

import java.util.Objects;

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

    public County getCounty() {
        return county;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostData postData = (PostData) o;
        return Objects.equals(postalCode, postData.postalCode) &&
                Objects.equals(city, postData.city) &&
                Objects.equals(municipality, postData.municipality) &&
                Objects.equals(county, postData.county);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCode, city, municipality, county);
    }
}