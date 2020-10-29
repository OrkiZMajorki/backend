package pl.orki.hackathon.webapp.venue.control;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.venue.entity.Venue;
import pl.orki.hackathon.webapp.venue.entity.VenueRepository;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Transactional
    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }
}
