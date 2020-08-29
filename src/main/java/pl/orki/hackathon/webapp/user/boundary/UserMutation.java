package pl.orki.hackathon.webapp.user.boundary;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.user.control.UserService;
import pl.orki.hackathon.webapp.user.entity.Role;
import pl.orki.hackathon.webapp.user.entity.User;

import static pl.orki.hackathon.webapp.user.boundary.UserConverter.convertToResponseDTO;

@Component
public class UserMutation implements GraphQLMutationResolver {

    private final UserService userService;

    public UserMutation(UserService userService) {
        this.userService = userService;
    }

    public UserResponseDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        userService.createUser(user);
        return convertToResponseDTO(user);
    }

    @NotNull
    private User convertToEntity(UserDTO userDTO) {
        var user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRole(Role.valueOf(userDTO.getRole()));
        user.setPassword(userDTO.getPassword());
        return user;
    }


}
