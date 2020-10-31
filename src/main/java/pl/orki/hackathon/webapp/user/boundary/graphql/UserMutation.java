package pl.orki.hackathon.webapp.user.boundary.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.band.boundary.dto.BandDTO;
import pl.orki.hackathon.webapp.user.boundary.UserDTO;
import pl.orki.hackathon.webapp.user.boundary.UserResponseDTO;
import pl.orki.hackathon.webapp.user.control.UserService;
import pl.orki.hackathon.webapp.venue.boundary.dto.VenueDTO;

@Component
public class UserMutation implements GraphQLMutationResolver {

    private final UserService userService;

    public UserMutation(UserService userService) {
        this.userService = userService;
    }

    public UserResponseDTO createUserBand(UserDTO userDTO, BandDTO bandDTO) {
        return userService.createUserBand(userDTO, bandDTO);
    }

    public UserResponseDTO createUserVenue(UserDTO userDTO, VenueDTO venueDTO) {
        return userService.createUserVenue(userDTO, venueDTO);
    }

}
