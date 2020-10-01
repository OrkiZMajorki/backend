package pl.orki.hackathon.webapp.band.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.orki.hackathon.webapp.city.entity.City;
import pl.orki.hackathon.webapp.genre.MusicGenre;

import java.util.List;

public interface BandRepository extends JpaRepository<Band, Long> {

    List<Band> findAllByMusicGenresInAndCitiesIn(List<MusicGenre> musicGenres, List<City> cities);
}
