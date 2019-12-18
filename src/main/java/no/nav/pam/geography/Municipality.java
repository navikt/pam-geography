package no.nav.pam.geography;

import java.util.Objects;

public class Municipality {

    private String code;
    private String name;

    public Municipality(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getCountyCode() {
        return code.substring(0,2);
    }

    public String getName() {
        return name;
    }

    /**
     * @return capitalized {@link #getName() municipality name}, e.g. "Sør-Fron" instead of "SØR-FRON"
     */
    public String getCapitalizedName() {
        return Names.capitalizeLocationName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Municipality that = (Municipality) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    @Override
    public String toString() {
        return "Municipality{" + code + " " + name + "}";
    }
}
