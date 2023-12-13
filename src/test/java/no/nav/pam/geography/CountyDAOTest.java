package no.nav.pam.geography;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CountyDAOTest {

    @Test
    public void should_find_name(){
        Optional<String> c  = CountyDAO.findCountyName("03");
        assertTrue(c.isPresent());
        assertEquals("OSLO", c.get());
    }

    @Test
    public void should_find_county(){
        Optional<County> c  = CountyDAO.findCounty("32");
        assertTrue(c.isPresent());
        assertEquals("AKERSHUS", c.get().getName());
    }

    @Test
    public void should_not_find_deprecated_counties(){
        assertFalse(CountyDAO.findCountyName("01").isPresent());
        assertFalse(CountyDAO.findCountyName("02").isPresent());
        assertFalse(CountyDAO.findCountyName("06").isPresent());
    }

}
