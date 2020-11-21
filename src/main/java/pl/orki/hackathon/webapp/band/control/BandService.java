package pl.orki.hackathon.webapp.band.control;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.band.entity.BandRepository;
import pl.orki.hackathon.webapp.city.entity.City;
import pl.orki.hackathon.webapp.city.entity.CityRepository;
import pl.orki.hackathon.webapp.genre.entity.MusicGenre;
import pl.orki.hackathon.webapp.genre.entity.MusicGenreRepository;

import java.util.List;

@Service
public class BandService {

    private final BandRepository bandRepository;
    private final CityRepository cityRepository;
    private final MusicGenreRepository musicGenreRepository;

    public BandService(BandRepository bandRepository, CityRepository cityRepository,
                       MusicGenreRepository musicGenreRepository) {
        this.bandRepository = bandRepository;
        this.cityRepository = cityRepository;
        this.musicGenreRepository = musicGenreRepository;
    }

    @Transactional(readOnly = true)
    public List<Band> getBandsByMusicGenresAndCities(List<Long> genresIds, List<Long> citiesIds) {
        List<MusicGenre> musicGenres = musicGenreRepository.findAllById(genresIds);
        List<City> cities = cityRepository.findAllById(citiesIds);

        return bandRepository.findAllByMusicGenresInAndCitiesIn(musicGenres, cities);
    }

    @Transactional
    public Band createBand(Band band) {
        return bandRepository.save(band);
    }
}
