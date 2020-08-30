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
        venue1.setMusicGenres(Set.of(MusicGenre.ROCK));

        venueRepository.save(venue1);

        var venue2 = new Venue();
        venue2.setName("Whiskey in the jar");
        venue2.setCapacity(300);
        venue2.setCity(City.WROCLAW);
        venue2.setMusicGenres(Set.of(MusicGenre.ROCK, MusicGenre.RAP));

        venueRepository.save(venue2);
    }

    private void createBands() {

        var band3 = new Band();
        band3.setName("Classic Folks");
        band3.setCities(Set.of(City.GDANSK, City.WROCLAW));
        band3.setDescription("Hello guys");
        band3.setMusicGenres(Set.of(MusicGenre.CLASSIC));
        band3.setSongName("Beethoven 12 Variation");
        band3.setSongUrl("http://www.hochmuth.com/mp3/Beethoven_12_Variation.mp3");
        band3.setImageUrl("https://images.unsplash.com/photo-1590377830274-93e66ae34415?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80&fbclid=IwAR2NPu6X1t9StmlWzJvRkwLX7-rexrNkRxsFJDgKAl0-h-iNsymdM9EruB4");

        bandRepository.save(band3);

        var band4 = new Band();
        band4.setName("Tchaikovsky new Band");
        band4.setCities(Set.of(City.CRACOV, City.WROCLAW));
        band4.setDescription("Hello");
        band4.setMusicGenres(Set.of(MusicGenre.CLASSIC));
        band4.setSongName("Rococo");
        band4.setSongUrl("http://www.hochmuth.com/mp3/Tchaikovsky_Rococo_Var_orch.mp3");
        band4.setImageUrl("https://images.unsplash.com/photo-1530522070995-aa23c19e77d1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80");

        bandRepository.save(band4);

        var band5 = new Band();
        band5.setName("The Haydn Guys");
        band5.setCities(Set.of(City.GDANSK, City.WROCLAW));
        band5.setDescription("");
        band5.setMusicGenres(Set.of(MusicGenre.CLASSIC));
        band5.setSongName("Adiago cover");
        band5.setSongUrl("http://www.hochmuth.com/mp3/Haydn_Adagio.mp3");
        band5.setImageUrl("https://images.unsplash.com/photo-1594122230689-7f659cff55b3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80&fbclid=IwAR3od3IGmT5R1c-U9IKgdmoQAVcM1Qwj7gJRyecOHo2insDepDKx-KE8_Yc");

        bandRepository.save(band5);

        var band6 = new Band();
        band6.setName("Concerting Live Girl");
        band6.setCities(Set.of(City.GDANSK, City.WROCLAW));
        band6.setDescription("");
        band6.setMusicGenres(Set.of(MusicGenre.CLASSIC));
        band6.setSongName("Concerto");
        band6.setSongUrl("http://www.hochmuth.com/mp3/Boccherini_Concerto_478-1.mp3");
        band6.setImageUrl("https://images.unsplash.com/photo-1545239674-02d3da343bd0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1650&q=80&fbclid=IwAR1lnzZigdSrpmBp0GwGQjgaJ-Ta3u9P3XPp5tyTB3pXV0lUWIjV2rp9rDQ");

        bandRepository.save(band6);
    }
}
