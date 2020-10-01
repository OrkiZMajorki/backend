package pl.orki.hackathon.webapp.venue.control;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.user.entity.UserRepository;
import pl.orki.hackathon.webapp.venue.entity.Venue;
import pl.orki.hackathon.webapp.venue.entity.VenueRepository;

import java.util.Optional;

@Service
public class VenueService {

    private final UserRepository userRepository;
    private final VenueRepository venueRepository;

    public VenueService(UserRepository userRepository, VenueRepository venueRepository) {
        this.userRepository = userRepository;
        this.venueRepository = venueRepository;
    }

    @Transactional
    public Optional<Venue> createVenue(Venue venue, Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    venue.setUser(user);
                    return venueRepository.save(venue);
                });
    }
}
