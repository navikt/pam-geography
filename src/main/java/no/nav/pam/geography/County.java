package no.nav.pam.geography;

import java.util.Objects;

public class County {

    private String code;
    private String name;

    public County(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        County county = (County) o;
        return Objects.equals(code, county.code) &&
                Objects.equals(name, county.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    @Override
    public String toString() {
        return "County{" + code + " " + name + "}";
    }
}
