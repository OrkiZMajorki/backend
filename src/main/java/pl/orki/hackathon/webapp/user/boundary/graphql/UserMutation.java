package pl.orki.hackathon.webapp.user.boundary.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.band.boundary.dto.BandConverter;
import pl.orki.hackathon.webapp.band.boundary.dto.BandDTO;
import pl.orki.hackathon.webapp.band.control.BandService;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.user.boundary.UserDTO;
import pl.orki.hackathon.webapp.user.boundary.UserResponseDTO;
import pl.orki.hackathon.webapp.user.control.UserService;
import pl.orki.hackathon.webapp.user.entity.User;
import pl.orki.hackathon.webapp.venue.boundary.dto.VenueConverter;
import pl.orki.hackathon.webapp.venue.boundary.dto.VenueDTO;
import pl.orki.hackathon.webapp.venue.control.VenueService;
import pl.orki.hackathon.webapp.venue.entity.Venue;

import java.util.Optional;

import static pl.orki.hackathon.webapp.user.boundary.UserConverter.convertToEntity;
import static pl.orki.hackathon.webapp.user.boundary.UserConverter.convertToResponseDTO;

@Component
public class UserMutation implements GraphQLMutationResolver {

    private final UserService userService;
    private final BandService bandService;
    private final VenueService venueService;
    private final BandConverter bandConverter;
    private final VenueConverter venueConverter;

    public UserMutation(UserService userService, BandService bandService, VenueService venueService, BandConverter bandConverter, VenueConverter venueConverter) {
        this.userService = userService;
        this.bandService = bandService;
        this.venueService = venueService;
        this.bandConverter = bandConverter;
        this.venueConverter = venueConverter;
    }

    public UserResponseDTO createUserBand(UserDTO userDTO, BandDTO bandDTO) {
        User user = convertToEntity(userDTO);
        Optional<Band> band = bandService.createBand(bandConverter.convertToEntity(bandDTO));
        band.ifPresent(value -> userService.createUser(user, value.getId()));
        return convertToResponseDTO(user);
    }


    public UserResponseDTO createUserVenue(UserDTO userDTO, VenueDTO venueDTO) {
        User user = convertToEntity(userDTO);
        Optional<Venue> venue = venueService.createVenue(venueConverter.convertToEntity(venueDTO));
        venue.ifPresent(value -> userService.createUser(user, value.getId()));
        return convertToResponseDTO(user);
    }

}
