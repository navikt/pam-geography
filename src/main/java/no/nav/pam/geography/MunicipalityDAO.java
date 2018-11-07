package no.nav.pam.geography;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MunicipalityDAO {

    private static final Logger LOG = LoggerFactory.getLogger(MunicipalityDAO.class);
    private final static String FILENAME = "postal_codes_no.tsv";

    private final Map<String, Municipality> municipalityMap;

    public MunicipalityDAO() throws IOException {

      municipalityMap = new HashMap<>();

      String line;
      String csvSplitBy = "\t";

      try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILENAME)) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF_8));
        while ((line = br.readLine()) != null) {
          String[] postArray = line.split(csvSplitBy);

          Municipality municipality = new Municipality(postArray[2], postArray[3]);
          if (municipality != null) {
            municipalityMap.put(municipality.getCode(), municipality);
          }
        }
      }

      LOG.debug("Imported the postal code table from file to memory for municipalities.");
    }

    public Optional<Municipality> findMunicipality(String municipalityCode) {
      return Optional.ofNullable(municipalityMap.get(municipalityCode));
    }

    public Set<Municipality> getAllMunicipalities() {
      return new HashSet<Municipality>(municipalityMap.values());
    }
  }

