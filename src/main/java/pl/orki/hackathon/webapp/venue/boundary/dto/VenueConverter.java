package pl.orki.hackathon.webapp.venue.boundary.dto;

import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.city.entity.City;
import pl.orki.hackathon.webapp.city.entity.CityRepository;
import pl.orki.hackathon.webapp.genre.MusicGenre;
import pl.orki.hackathon.webapp.venue.entity.Venue;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VenueConverter {

    private final CityRepository cityRepository;

    public VenueConverter(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Venue convertToEntity(VenueDTO venueDTO) {
        var venue = new Venue();
        venue.setId(venueDTO.getId());
        venue.setName(venueDTO.getName());
        venue.setCapacity(venueDTO.getCapacity());
        var cityId = venueDTO.getCityId();
        cityRepository.findById(cityId).ifPresent(venue::setCity);
        Set<String> musicGenres = venueDTO.getMusicGenres();
        if (musicGenres != null) {
            venue.setMusicGenres(convertToMusicGenres(musicGenres));
        }

        return venue;
    }

    public VenueResponseDTO convertToDTO(Venue venue) {
        var venueDTO = new VenueResponseDTO();
        venueDTO.setId(venue.getId());
        venueDTO.setName(venue.getName());
        venueDTO.setCapacity(venue.getCapacity());
        City city = venue.getCity();
        if (city != null) {
            venueDTO.setCityId(city.getId());
            venueDTO.setCity(city.getName());
        }
        venueDTO.setMusicGenres(convertToNamesOfMusicGenres(venue));

        return venueDTO;
    }

    private Set<MusicGenre> convertToMusicGenres(Set<String> musicGenres) {
        return musicGenres.stream()
                .map(MusicGenre::valueOf)
                .collect(Collectors.toSet());
    }

    private Set<String> convertToNamesOfMusicGenres(Venue venue) {
        Set<MusicGenre> musicGenres = venue.getMusicGenres();

        if (musicGenres == null) {
            return Set.of();
        }
        else {
            return musicGenres.stream().map(MusicGenre::toString).collect(Collectors.toSet());
        }
    }
}
