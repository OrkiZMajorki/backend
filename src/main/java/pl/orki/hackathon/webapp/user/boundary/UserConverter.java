package pl.orki.hackathon.webapp.user.boundary;

import pl.orki.hackathon.webapp.user.entity.User;

public class UserConverter {
    private UserConverter() {
    }

    public static UserResponseDTO convertToResponseDTO(User user) {
        var dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole().toString());

        return dto;
    }
}
