package pl.orki.hackathon.webapp.user.boundary.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.user.boundary.UserConverter;
import pl.orki.hackathon.webapp.user.boundary.UserLoginDTO;
import pl.orki.hackathon.webapp.user.boundary.UserResponseDTO;
import pl.orki.hackathon.webapp.user.control.UserService;

import java.util.Optional;

@Component
public class UserQuery implements GraphQLQueryResolver {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserQuery(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    public Optional<UserResponseDTO> login(UserLoginDTO userLoginDTO) {
        return userService.authenticateUser(userLoginDTO).map(userConverter::convertToResponseDTO);
    }
}
