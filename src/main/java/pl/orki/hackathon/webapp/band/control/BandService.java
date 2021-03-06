package pl.orki.hackathon.webapp.band.control;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.band.entity.BandRepository;
import pl.orki.hackathon.webapp.city.entity.City;
import pl.orki.hackathon.webapp.city.entity.CityRepository;
import pl.orki.hackathon.webapp.genre.MusicGenre;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BandService {

    private final BandRepository bandRepository;
    private final CityRepository cityRepository;

    public BandService(BandRepository bandRepository, CityRepository cityRepository) {
        this.bandRepository = bandRepository;
        this.cityRepository = cityRepository;
    }

    @Transactional(readOnly = true)
    public List<Band> getBandsByMusicGenresAndCities(List<String> genresNames, List<Long> citiesIds) {
        List<City> cities = cityRepository.findAllById(citiesIds);
        // TODO: it will be replaced by querying genres by ids
        List<MusicGenre> genres = genresNames.stream()
                .map(MusicGenre::valueOf)
                .collect(Collectors.toList());

        return bandRepository.findAllByMusicGenresInAndCitiesIn(genres, cities);
    }

    @Transactional
    public Band createBand(Band band) {
        return bandRepository.save(band);
    }
}
