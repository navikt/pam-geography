package no.nav.pam.geography;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MunicipalityDAO {

    private final Map<String, Municipality> municipalityMap;

    public MunicipalityDAO() throws IOException {
        municipalityMap = new PostDataDAO().getAllMunicipalities().stream()
                .collect(Collectors.toMap(Municipality::getCode, Function.identity()));
    }

    public Optional<Municipality> findMunicipality(String municipalityCode) {
        return Optional.ofNullable(municipalityMap.get(municipalityCode));
    }

    public Set<Municipality> getAllMunicipalities() {
        return new HashSet<Municipality>(municipalityMap.values());
    }
}

