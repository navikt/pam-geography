package no.nav.pam.geography.kor2020;

import no.nav.pam.geography.Municipality;
import no.nav.pam.geography.MunicipalityDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Data source:
 * https://kartverket.no/kommunereform/tekniske-endringer/kommune--og-regionsendringer-2020/
 */
public class MunicipalityMergerSupport {

    static final String MERGERS_RESOURCE = "/kor2020/municipality-mergers.tsv";

    private final MunicipalityDAO municipalityDao;
    private final Map<String,String> transitions;

    public MunicipalityMergerSupport(MunicipalityDAO municipalityDao) throws IOException {
        this.municipalityDao = Objects.requireNonNull(municipalityDao);
        this.transitions = loadTransitions();
    }

    /**
     * Lookup a municipality by code, old or new. If code is for a pre-2020 municipality that no longer exists after 2020,
     * the corresponding new municipality will be returned. Otherwise, a normal lookup is performed,
     * like {@link MunicipalityDAO#findMunicipality(String)}.
     *
     * @param oldOrNewCode an old (pre-merger) or new (post-merger) municipality code
     * @return a transitioned or existing municipality if either old or new code is known, otherwise empty optional
     */
    public Optional<Municipality> findByCode(String oldOrNewCode) {
        String transitionedCode = transitions.get(oldOrNewCode);
        return transitionedCode != null ?
                municipalityDao.findMunicipality(transitionedCode) : municipalityDao.findMunicipality(oldOrNewCode);
    }

    private Map<String,String> loadTransitions() throws IOException {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(
                        getClass().getResourceAsStream(MERGERS_RESOURCE), StandardCharsets.UTF_8))) {
            return lineReader.lines()
                    .filter(line -> !line.startsWith("#"))
                    .map(line -> line.split("\t"))
                    .collect(Collectors.toMap(
                            fields -> {
                                if (fields.length != 4) {
                                    throw new IllegalStateException("Unexpected number of fields detected in data file");
                                }
                                return fields[1].split(" ")[0];
                            },
                            fields -> fields[3].split(" ")[0],
                            (v1,v2) -> v1));
        }
    }

}
