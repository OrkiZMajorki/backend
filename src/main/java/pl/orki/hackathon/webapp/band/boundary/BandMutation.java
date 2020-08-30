package pl.orki.hackathon.webapp.band.boundary;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.band.control.BandService;
import pl.orki.hackathon.webapp.city.City;
import pl.orki.hackathon.webapp.genre.MusicGenre;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BandMutation implements GraphQLMutationResolver {

    private final BandService bandService;

    public BandMutation(BandService bandService) {
        this.bandService = bandService;
    }

    public Optional<BandDTO> createBand(BandDTO bandDTO, Long userId) {
        Band band = convertToEntity(bandDTO);
        return bandService.createBand(band, userId)
            .map(BandConverter::convertToDTO);
    }

    private Band convertToEntity(BandDTO bandDTO) {
        var band = new Band();
        band.setId(bandDTO.getId());
        band.setName(bandDTO.getName());
        band.setDescription(bandDTO.getDescription());
        band.setImageUrl(bandDTO.getImageUrl());
        band.setSongUrl(bandDTO.getSongUrl());
        band.setSongName(bandDTO.getSongName());
        band.setCities(convertCities(bandDTO));
        band.setMusicGenres(convertGenres(bandDTO));

        return band;
    }

    @NotNull
    private Set<City> convertCities(BandDTO bandDTO) {
        Set<String> cities = bandDTO.getCities();
        if (cities == null) {
            return Set.of();
        }
        return cities.stream().map(City::valueOf).collect(Collectors.toSet());
    }

    @NotNull
    private Set<MusicGenre> convertGenres(BandDTO bandDTO) {
        Set<String> musicGenres = bandDTO.getMusicGenres();
        if (musicGenres == null) {
            return Set.of();
        }
        return musicGenres.stream().map(MusicGenre::valueOf).collect(Collectors.toSet());
    }
}
