package pl.orki.hackathon.webapp.venue.boundary.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.venue.boundary.dto.VenueConverter;
import pl.orki.hackathon.webapp.venue.boundary.dto.VenueDTO;
import pl.orki.hackathon.webapp.venue.boundary.dto.VenueResponseDTO;
import pl.orki.hackathon.webapp.venue.control.VenueService;

import java.util.Optional;

@Component
public class VenueMutation implements GraphQLMutationResolver {

    private final VenueService venueService;
    private final VenueConverter venueConverter;

    public VenueMutation(VenueService venueService, VenueConverter venueConverter) {
        this.venueService = venueService;
        this.venueConverter = venueConverter;
    }

    @Transactional
    public Optional<VenueResponseDTO> createVenue(VenueDTO venueDTO, Long userId) {
        var venue = venueConverter.convertToEntity(venueDTO);

        return venueService.createVenue(venue, userId)
                .map(venueConverter::convertToDTO);
    }
}
