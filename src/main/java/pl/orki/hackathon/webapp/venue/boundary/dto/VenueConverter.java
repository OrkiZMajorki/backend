package pl.orki.hackathon.webapp.venue.boundary.dto;

import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.city.entity.City;
import pl.orki.hackathon.webapp.city.entity.CityRepository;
import pl.orki.hackathon.webapp.genre.entity.MusicGenre;
import pl.orki.hackathon.webapp.genre.entity.MusicGenreRepository;
import pl.orki.hackathon.webapp.venue.entity.Venue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VenueConverter {

    private final CityRepository cityRepository;
    private final MusicGenreRepository musicGenreRepository;

    public VenueConverter(CityRepository cityRepository, MusicGenreRepository musicGenreRepository) {
        this.cityRepository = cityRepository;
        this.musicGenreRepository = musicGenreRepository;
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
        Set<MusicGenre> musicGenres = venue.getMusicGenres();
        venueDTO.setMusicGenres(getMusicGenresNames(musicGenres));
        venueDTO.setMusicGenresIds(getMusicGenresIds(musicGenres));

        return venueDTO;
    }

    public Venue convertToEntity(VenueDTO venueDTO) {
        var venue = new Venue();
        venue.setId(venueDTO.getId());
        venue.setName(venueDTO.getName());
        venue.setCapacity(venueDTO.getCapacity());
        var cityId = venueDTO.getCityId();
        cityRepository.findById(cityId).ifPresent(venue::setCity);
        venue.setMusicGenres(convertMusicGenres(venueDTO.getMusicGenresIds()));

        return venue;
    }

    private Set<String> getMusicGenresNames(Set<MusicGenre> musicGenres) {
        return musicGenres.stream()
                .map(MusicGenre::toString)
                .collect(Collectors.toSet());
    }

    private Set<Long> getMusicGenresIds(Set<MusicGenre> musicGenres) {
        return musicGenres.stream()
                .map(MusicGenre::getId)
                .collect(Collectors.toSet());
    }

    private Set<MusicGenre> convertMusicGenres(Set<Long> musicGenresIds) {
        return new HashSet<>(musicGenreRepository.findAllById(musicGenresIds));
    }
}
