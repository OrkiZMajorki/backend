package pl.orki.hackathon.webapp.band.boundary;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.band.boundary.dto.BandConverter;
import pl.orki.hackathon.webapp.band.boundary.dto.BandDTO;
import pl.orki.hackathon.webapp.band.boundary.dto.BandResponseDTO;
import pl.orki.hackathon.webapp.band.control.BandService;
import pl.orki.hackathon.webapp.band.entity.Band;

import java.util.Optional;

@Component
public class BandMutation implements GraphQLMutationResolver {

    private final BandService bandService;
    private final BandConverter bandConverter;

    public BandMutation(BandService bandService, BandConverter bandConverter) {
        this.bandService = bandService;
        this.bandConverter = bandConverter;
    }

    @Transactional
    public Optional<BandResponseDTO> createBand(BandDTO bandDTO, Long userId) {
        Band band = bandConverter.convertToEntity(bandDTO);
        return bandService.createBand(band, userId)
                .map(bandConverter::convertToDTO);
    }
}
