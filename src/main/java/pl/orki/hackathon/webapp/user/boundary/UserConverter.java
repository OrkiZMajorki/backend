package pl.orki.hackathon.webapp.user.boundary;

import org.jetbrains.annotations.NotNull;
import pl.orki.hackathon.webapp.band.boundary.dto.BandResponseDTO;
import pl.orki.hackathon.webapp.user.entity.User;
import pl.orki.hackathon.webapp.venue.boundary.dto.VenueResponseDTO;

public class UserConverter {
    private UserConverter() {
    }

    public static UserResponseDTO convertToResponseDTOWithVenue(User user, VenueResponseDTO venueDTO) {
        UserResponseDTO userResponseDTO = convertToResponseDTO(user);
        userResponseDTO.setVenue(venueDTO);
        return userResponseDTO;
    }

    public static UserResponseDTO convertToResponseDTOWithBand(User user, BandResponseDTO bandDTO) {
        UserResponseDTO userResponseDTO = convertToResponseDTO(user);
        userResponseDTO.setBand(bandDTO);
        return userResponseDTO;
    }

    public static UserResponseDTO convertToResponseDTO(User user) {
        var dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        return dto;
    }

    @NotNull
    public static User convertToEntity(UserDTO userDTO) {
        var user = new pl.orki.hackathon.webapp.user.entity.User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
