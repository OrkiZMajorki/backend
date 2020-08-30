package pl.orki.hackathon.webapp.venue.boundary.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.city.City;
import pl.orki.hackathon.webapp.genre.MusicGenre;
import pl.orki.hackathon.webapp.venue.boundary.VenueDTO;
import pl.orki.hackathon.webapp.venue.control.VenueService;
import pl.orki.hackathon.webapp.venue.entity.Venue;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VenueMutation implements GraphQLMutationResolver {

    private final VenueService venueService;

    public VenueMutation(VenueService venueService) {
        this.venueService = venueService;
    }

    public Optional<VenueDTO> createVenue(VenueDTO venueDTO, Long userId) {
        var venue = convertToEntity(venueDTO);

        return venueService.createVenue(venue, userId)
            .map(this::convertToDTO);
    }

    private Venue convertToEntity(VenueDTO venueDTO) {
        var venue = new Venue();
        venue.setId(venueDTO.getId());
        venue.setName(venueDTO.getName());
        venue.setCapacity(venueDTO.getCapacity());
        String city = venueDTO.getCity();
        if (city != null) {
            venue.setCity(City.valueOf(city));
        }
        Set<String> musicGenres = venueDTO.getMusicGenres();
        if (musicGenres != null) {
            venue.setMusicGenres(convertToMusicGenres(musicGenres));
        }

        return venue;
    }

    private Set<MusicGenre> convertToMusicGenres(Set<String> musicGenres) {
        return musicGenres.stream()
                .map(MusicGenre::valueOf)
                .collect(Collectors.toSet());
    }

    private VenueDTO convertToDTO(Venue venue) {
        var venueDTO = new VenueDTO();
        venueDTO.setId(venue.getId());
        venueDTO.setName(venue.getName());
        venueDTO.setCapacity(venue.getCapacity());
        City city = venue.getCity();
        if (city != null) {
            venueDTO.setCity(city.toString());
        }
        venueDTO.setMusicGenres(convertToNamesOfMusicGenres(venue));

        return venueDTO;
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
