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
        bandDTO.setName(band.getName());
        bandDTO.setDescription(band.getDescription());
        bandDTO.setSongUrl(band.getSongUrl());
        bandDTO.setSongName(band.getName());
        bandDTO.setImageUrl(band.getImageUrl());
        bandDTO.setCities(band.getCities().stream().map(City::toString).collect(Collectors.toSet()));
        bandDTO.setMusicGenres(band.getMusicGenres().stream().map(MusicGenre::toString).collect(Collectors.toSet()));

        return bandDTO;
    }
}
