package pl.orki.hackathon.webapp.band.control;

import org.springframework.stereotype.Service;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.band.entity.BandRepository;
import pl.orki.hackathon.webapp.user.entity.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class BandService {

    private final BandRepository bandRepository;
    private final UserRepository userRepository;

    public BandService(BandRepository bandRepository, UserRepository userRepository) {
        this.bandRepository = bandRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<Band> createBand(Band band, Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    band.setUser(user);
                    return bandRepository.save(band);
                });
    }
}
