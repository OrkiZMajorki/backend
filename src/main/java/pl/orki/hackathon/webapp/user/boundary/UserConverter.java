package pl.orki.hackathon.webapp.user.boundary;

import org.jetbrains.annotations.NotNull;
import pl.orki.hackathon.webapp.user.entity.Role;

public class UserConverter {
    private UserConverter() {
    }

    public static UserResponseDTO convertToResponseDTO(pl.orki.hackathon.webapp.user.entity.User user) {
        var dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole().toString());

        return dto;
    }

    @NotNull
    public static pl.orki.hackathon.webapp.user.entity.User convertToEntity(UserDTO userDTO) {
        var user = new pl.orki.hackathon.webapp.user.entity.User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRole(Role.valueOf(userDTO.getRole()));
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
