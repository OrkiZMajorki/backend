package pl.orki.hackathon.webapp.band.boundary;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.band.entity.BandRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BandQuery implements GraphQLQueryResolver {

    private final BandRepository bandRepository;

    public BandQuery(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @Transactional(readOnly = true)
    public Set<Band> findBandsByGenreAndCity(Set<String> genres, Set<String> cities) {
        return bandRepository.findAll().stream()
                .filter(band -> CollectionUtils.containsAny(genres, band.getMusicGenres()))
                .filter(band -> CollectionUtils.containsAny(cities, band.getCities()))
                .collect(Collectors.toSet());
    }

}
