package pl.orki.hackathon.webapp.user.control;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.user.boundary.UserLoginDTO;
import pl.orki.hackathon.webapp.user.entity.User;
import pl.orki.hackathon.webapp.user.entity.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Optional<User> authenticateUser(UserLoginDTO userLoginDTO) {
        return userRepository.findByEmail(userLoginDTO.getEmail())
                .filter(user -> bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), user.getPassword()));
    }

    @Transactional
    public User createUser(User user, Long roleId) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoleId(roleId);
        return userRepository.save(user);
    }
}
