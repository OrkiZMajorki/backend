package pl.orki.hackathon.webapp.band.boundary.dto;

import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.city.entity.City;
import pl.orki.hackathon.webapp.city.entity.CityRepository;
import pl.orki.hackathon.webapp.genre.MusicGenre;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BandConverter {

    private final CityRepository cityRepository;

    public BandConverter(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public BandResponseDTO convertToDTO(Band band) {
        var bandDTO = new BandResponseDTO();
        bandDTO.setId(band.getId());
        bandDTO.setName(band.getName());
        bandDTO.setDescription(band.getDescription());
        bandDTO.setSongUrl(band.getSongUrl());
        bandDTO.setSongName(band.getSongName());
        bandDTO.setImageUrl(band.getImageUrl());
        Set<City> cities = band.getCities();
        bandDTO.setCitiesIds(getCitiesIds(cities));
        bandDTO.setCities(getCitiesNames(cities));
        bandDTO.setMusicGenres(band.getMusicGenres().stream().map(MusicGenre::toString).collect(Collectors.toSet()));

        return bandDTO;
    }

    public Band convertToEntity(BandDTO bandDTO) {
        var band = new Band();
        band.setId(bandDTO.getId());
        band.setName(bandDTO.getName());
        band.setDescription(bandDTO.getDescription());
        band.setImageUrl(bandDTO.getImageUrl());
        band.setSongUrl(bandDTO.getSongUrl());
        band.setSongName(bandDTO.getSongName());
        band.setCities(convertCities(bandDTO.getCitiesIds()));
        band.setMusicGenres(convertGenres(bandDTO));

        return band;
    }

    private Set<Long> getCitiesIds(Set<City> cities) {
        return cities.stream()
                .map(City::getId)
                .collect(Collectors.toSet());
    }

    private Set<String> getCitiesNames(Set<City> cities) {
        return cities.stream()
                .map(City::getName)
                .collect(Collectors.toSet());
    }

    private Set<City> convertCities(Set<Long> citiesIds) {
        return new HashSet<>(cityRepository.findAllById(citiesIds));
    }

    private Set<MusicGenre> convertGenres(BandDTO bandDTO) {
        Set<String> musicGenres = bandDTO.getMusicGenres();
        if (musicGenres == null) {
            return Set.of();
        }
        return musicGenres.stream().map(MusicGenre::valueOf).collect(Collectors.toSet());
    }
}
