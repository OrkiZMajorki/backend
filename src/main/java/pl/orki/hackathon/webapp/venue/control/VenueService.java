package pl.orki.hackathon.webapp.venue.control;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.user.control.UserService;
import pl.orki.hackathon.webapp.venue.entity.Venue;
import pl.orki.hackathon.webapp.venue.entity.VenueRepository;

import java.util.Optional;

@Service
public class VenueService {

    private final VenueRepository venueRepository;
    private final UserService userService;

    public VenueService(VenueRepository venueRepository, UserService userService) {
        this.venueRepository = venueRepository;
        this.userService = userService;
    }

    @Transactional
    public Optional<Venue> createVenue(Venue venue) {
        userService.createUser(venue.getUser());
        return Optional.of(venueRepository.save(venue));
    }
}
