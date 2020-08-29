package pl.orki.hackathon.webapp.user.boundary;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.user.control.UserService;

import java.util.Optional;

@Component
public class UserQuery implements GraphQLQueryResolver {

    private final UserService userService;

    public UserQuery(UserService userService) {
        this.userService = userService;
    }

    public Optional<UserResponseDTO> login(UserLoginDTO userLoginDTO) {
        return userService.authenticateUser(userLoginDTO).map(UserConverter::convertToResponseDTO);
    }
}
