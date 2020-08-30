package pl.orki.hackathon.webapp.band.boundary;

import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.city.City;
import pl.orki.hackathon.webapp.genre.MusicGenre;

import java.util.stream.Collectors;

public class BandConverter {

    private BandConverter() {}

    public static BandDTO convertToDTO(Band band) {
        var bandDTO = new BandDTO();
        bandDTO.setId(band.getId());
        bandDTO.setName(bandDTO.getName());
        bandDTO.setDescription(bandDTO.getDescription());
        bandDTO.setSongUrl(bandDTO.getSongUrl());
        bandDTO.setImageUrl(bandDTO.getImageUrl());
        bandDTO.setCities(band.getCities().stream().map(City::toString).collect(Collectors.toSet()));
        bandDTO.setMusicGenres(band.getMusicGenres().stream().map(MusicGenre::toString).collect(Collectors.toSet()));

        return bandDTO;
    }
}
