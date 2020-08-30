package pl.orki.hackathon.webapp.band.boundary;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.band.control.BandService;
import pl.orki.hackathon.webapp.city.City;
import pl.orki.hackathon.webapp.genre.MusicGenre;

import java.util.stream.Collectors;

@Component
public class BandMutation implements GraphQLMutationResolver {

    private final BandService bandService;

    public BandMutation(BandService bandService) {
        this.bandService = bandService;
    }

    public BandDTO createBand(BandDTO bandDTO) {
        Band band = convertToEntity(bandDTO);
        bandService.createBand(band);
        return bandDTO;
    }

    private Band convertToEntity(BandDTO bandDTO) {
        var band = new Band();
        band.setId(bandDTO.getId());
        band.setName(bandDTO.getName());
        band.setDescription(bandDTO.getDescription());
        band.setImageUrl(bandDTO.getImageUrl());
        band.setSongUrl(bandDTO.getSongUrl());
        band.setSongName(bandDTO.getSongName());
        band.setCities(bandDTO.getCities().stream().map(City::valueOf).collect(Collectors.toSet()));
        band.setMusicGenres(bandDTO.getMusicGenres().stream().map(MusicGenre::valueOf).collect(Collectors.toSet()));

        return band;
    }
}
