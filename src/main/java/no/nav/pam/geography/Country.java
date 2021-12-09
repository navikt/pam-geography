package no.nav.pam.geography;


import java.util.Objects;

public class Country {

    private String alpha2Code;
    private String alpha3Code;
    private String name;

    public Country(String alpha2Code, String alpha3Code, String name) {
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.name = name;
    }

    /**
     * @deprecated Use either {@link #getAlpha2Code()} or {@link #getAlpha3Code()}
     */
    public String getCode() {
        return alpha2Code;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public String getName() {
        return name;
    }

    /**
     * @return capitalized {@link #getName() country name}, e.g. "Norway" instead of "NORWAY".
     */
    public String getCapitalizedName() {
        return Names.capitalizeLocationName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(alpha2Code, country.alpha2Code) && Objects.equals(alpha3Code, country.alpha3Code) &&
                   Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alpha2Code, alpha3Code, name);
    }

    @Override
    public String toString() {
        return "Country{" + alpha2Code + " " + alpha3Code + " " + name + "}";
    }
}
