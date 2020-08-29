package pl.orki.hackathon.webapp.band.control;

import org.springframework.stereotype.Service;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.band.entity.BandRepository;

import javax.transaction.Transactional;

@Service
public class BandService {

    private final BandRepository bandRepository;

    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @Transactional
    public Band createBand(Band band) {
        return bandRepository.save(band);
    }
}
