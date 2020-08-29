package pl.orki.hackathon.webapp.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.orki.hackathon.webapp.user.boundary.UserLoginDTO;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
