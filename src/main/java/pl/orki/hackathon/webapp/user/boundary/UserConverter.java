package pl.orki.hackathon.webapp.user.boundary;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.band.boundary.dto.BandResponseDTO;
import pl.orki.hackathon.webapp.user.entity.User;
import pl.orki.hackathon.webapp.venue.boundary.dto.VenueResponseDTO;

@Component
public class UserConverter {

    public UserResponseDTO convertToResponseDTOWithVenue(User user, VenueResponseDTO venueDTO) {
        UserResponseDTO userResponseDTO = convertToResponseDTO(user);
        userResponseDTO.setVenue(venueDTO);
        return userResponseDTO;
    }

    public UserResponseDTO convertToResponseDTOWithBand(User user, BandResponseDTO bandDTO) {
        UserResponseDTO userResponseDTO = convertToResponseDTO(user);
        userResponseDTO.setBand(bandDTO);
        return userResponseDTO;
    }

    public UserResponseDTO convertToResponseDTO(User user) {
        var dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        return dto;
    }

    @NotNull
    public User convertToEntity(UserDTO userDTO) {
        var user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
