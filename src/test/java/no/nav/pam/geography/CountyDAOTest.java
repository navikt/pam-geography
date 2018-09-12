package no.nav.pam.geography;

import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CountyDAOTest {

    @Test
    public void should_find_name(){
        Optional<String> c  = CountyDAO.findCountyName("01");
        assertTrue(c.isPresent());
        assertEquals("ØSTFOLD", c.get());

        assertFalse(CountyDAO.findCountyName("34").isPresent());
    }

    @Test
    public void should_find_county(){
        Optional<County> c  = CountyDAO.findCounty("01");
        assertTrue(c.isPresent());
        assertEquals("ØSTFOLD", c.get().getName());

        assertFalse(CountyDAO.findCountyName("34").isPresent());
    }
}
