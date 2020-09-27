package pl.orki.hackathon.webapp.band.boundary;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.band.boundary.dto.BandConverter;
import pl.orki.hackathon.webapp.band.boundary.dto.BandResponseDTO;
import pl.orki.hackathon.webapp.band.control.BandService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BandQuery implements GraphQLQueryResolver {

    private final BandService bandService;
    private final BandConverter bandConverter;

    public BandQuery(BandService bandService, BandConverter bandConverter) {
        this.bandService = bandService;
        this.bandConverter = bandConverter;
    }

    @Transactional(readOnly = true)
    public Set<BandResponseDTO> findBandsByGenreAndCity(List<String> genres, List<Long> citiesIds) {
        return bandService.getBandsByMusicGenresAndCities(genres, citiesIds)
                .stream()
                .map(bandConverter::convertToDTO)
                .collect(Collectors.toSet());
    }
}
