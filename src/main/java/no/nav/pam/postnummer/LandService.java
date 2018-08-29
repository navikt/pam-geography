package no.nav.pam.postnummer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class LandService {

    private static final Logger LOG = LoggerFactory.getLogger(LandService.class);
    private final static String FILENAME = "verdensland.csv";

    private final List<Land> landListe;

    public LandService() {
        landListe = new ArrayList();
    }

    @PostConstruct
    public LandService init() throws Exception {

        String line;
        String csvSplitBy = ";";

        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(FILENAME), UTF_8));
        while ((line = br.readLine()) != null) {
            String[] landArray = line.split(csvSplitBy);

            Land land = new Land(landArray[1], landArray[2], landArray[0]);
            landListe.add(land);
        }

        LOG.info("Imported the postal code table from file to memory.");
        return this;
    }

    public List<Land> getLandListe() {
        return landListe;
    }

    public static class Land {

        private String iso2;
        private String iso3;
        private String name;

        public Land(String iso2, String iso3, String name) {
            this.iso2 = iso2;
            this.iso3 = iso3;
            this.name = name;
        }

        public String getIso2() {
            return iso2;
        }

        public void setIso2(String iso2) {
            this.iso2 = iso2;
        }

        public String getIso3() {
            return iso3;
        }

        public void setIso3(String iso3) {
            this.iso3 = iso3;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
