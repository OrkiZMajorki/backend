package pl.orki.hackathon.webapp;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.band.entity.BandRepository;
import pl.orki.hackathon.webapp.city.City;
import pl.orki.hackathon.webapp.genre.MusicGenre;
import pl.orki.hackathon.webapp.venue.entity.Venue;
import pl.orki.hackathon.webapp.venue.entity.VenueRepository;

import java.util.Set;

@Component
public class InitialDataProvider implements ApplicationRunner {

    private final VenueRepository venueRepository;
    private final BandRepository bandRepository;

    public InitialDataProvider(VenueRepository venueRepository, BandRepository bandRepository) {
        this.venueRepository = venueRepository;
        this.bandRepository = bandRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createVenues();
        createBands();
    }

    private void createVenues() {
        var venue1 = new Venue();
        venue1.setName("Szwalnia");
        venue1.setCapacity(100);
        venue1.setCity(City.CRACOV);
        venue1.setMusicGenre(Set.of(MusicGenre.ROCK));

        venueRepository.save(venue1);

        var venue2 = new Venue();
        venue2.setName("Whiskey in the jar");
        venue2.setCapacity(300);
        venue2.setCity(City.WROCLAW);
        venue2.setMusicGenre(Set.of(MusicGenre.ROCK, MusicGenre.RAP));

        venueRepository.save(venue2);
    }

    private void createBands() {
        var band1 = new Band();
        band1.setName("Defekt muzgó");
        band1.setCity(Set.of(City.CRACOV, City.GDANSK, City.WROCLAW));
        band1.setDescription("Wszyscy jedziemy na tym samym wózku");
        band1.setMusicGenre(Set.of(MusicGenre.ROCK));

        bandRepository.save(band1);

        var band2 = new Band();
        band2.setName("Nagły atak spawacza");
        band2.setCity(Set.of(City.GDANSK, City.WROCLAW));
        band2.setDescription("Poznań jeżyce");
        band2.setMusicGenre(Set.of(MusicGenre.RAP));

        bandRepository.save(band2);
    }
}
