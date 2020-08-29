package pl.orki.hackathon.webapp.band.boundary;

import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.band.control.BandService;

@Component
public class BandMutation {
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
        band.setName(bandDTO.getName());
        band.setDescription(bandDTO.getDescription());
        band.setImageUrl(bandDTO.getImageUrl());
        band.setSongUrl(bandDTO.getSongUrl());
        band.setCities(bandDTO.getCity());
        band.setMusicGenres(bandDTO.getMusicGenres());
        return band;
    }

}
