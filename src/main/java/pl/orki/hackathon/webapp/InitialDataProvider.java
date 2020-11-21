package pl.orki.hackathon.webapp;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.band.entity.BandRepository;
import pl.orki.hackathon.webapp.city.entity.City;
import pl.orki.hackathon.webapp.city.entity.CityRepository;
import pl.orki.hackathon.webapp.genre.entity.MusicGenre;
import pl.orki.hackathon.webapp.genre.entity.MusicGenreRepository;
import pl.orki.hackathon.webapp.venue.entity.Venue;
import pl.orki.hackathon.webapp.venue.entity.VenueRepository;

import java.util.Set;

@Component
public class InitialDataProvider implements ApplicationRunner {

    private final VenueRepository venueRepository;
    private final BandRepository bandRepository;
    private final CityRepository cityRepository;
    private final MusicGenreRepository musicGenreRepository;

    private Long cityKrakowId;
    private Long cityWroclawId;
    private Long cityGdanskId;
    private Long genreRockId;
    private Long genreRapId;
    private Long genreClassicId;

    public InitialDataProvider(VenueRepository venueRepository, BandRepository bandRepository,
                               CityRepository cityRepository, MusicGenreRepository musicGenreRepository) {
        this.venueRepository = venueRepository;
        this.bandRepository = bandRepository;
        this.cityRepository = cityRepository;
        this.musicGenreRepository = musicGenreRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        createCities();
        createGenres();
        createVenues();
        createBands();
    }

    private void createCities() {
        this.cityKrakowId = createCity("Kraków");
        this.cityWroclawId = createCity("Wrocław");
        this.cityGdanskId = createCity("Gdańsk");
    }

    private Long createCity(String name) {
        var city = new City();
        city.setName(name);

        return cityRepository.save(city).getId();
    }

    private void createGenres() {
        this.genreRockId = createMusicGenre("Kraków");
        this.genreRapId = createMusicGenre("Wrocław");
        this.genreClassicId = createMusicGenre("Gdańsk");
    }

    private Long createMusicGenre(String name) {
        var musicGenre = new MusicGenre();
        musicGenre.setName(name);

        return musicGenreRepository.save(musicGenre).getId();
    }

    private void createVenues() {
        var cityKrakow = cityRepository.findById(cityKrakowId).get();
        var cityWroclaw = cityRepository.findById(cityWroclawId).get();
        var genreRock = musicGenreRepository.findById(this.genreRockId).get();
        var genreRap = musicGenreRepository.findById(this.genreRapId).get();
        
        var venue1 = new Venue();
        venue1.setName("Szwalnia");
        venue1.setCapacity(100);
        venue1.setCity(cityKrakow);
        venue1.setMusicGenres(Set.of(genreRock));

        venueRepository.save(venue1);

        var venue2 = new Venue();
        venue2.setName("Whiskey in the jar");
        venue2.setCapacity(300);
        venue2.setCity(cityWroclaw);
        venue2.setMusicGenres(Set.of(genreRock, genreRap));

        venueRepository.save(venue2);
    }

    private void createBands() {
        City cityKrakow = cityRepository.findById(cityKrakowId).get();
        City cityWroclaw = cityRepository.findById(cityWroclawId).get();
        City cityGdansk = cityRepository.findById(cityGdanskId).get();
        var genreRock = musicGenreRepository.findById(this.genreRockId).get();
        var genreClassic = musicGenreRepository.findById(this.genreClassicId).get();

        var band3 = new Band();
        band3.setName("Chris On Keys");
        band3.setDescription("When you witness Chris On Keys, you’ll soon see that piano truly is his forte! Having received classical training as a youngster, Chris soon progressed to jazz and classical music.");
        band3.setMusicGenres(Set.of(genreClassic));
        band3.setSongName("Beethoven 12 Variation");
        band3.setCities(Set.of(cityGdansk, cityWroclaw));
        band3.setSongUrl("http://www.hochmuth.com/mp3/Beethoven_12_Variation.mp3");
        band3.setImageUrl("https://images.unsplash.com/photo-1590377830274-93e66ae34415?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80&fbclid=IwAR2NPu6X1t9StmlWzJvRkwLX7-rexrNkRxsFJDgKAl0-h-iNsymdM9EruB4");
        bandRepository.save(band3);

        var band4 = new Band();
        band4.setName("Tchaikovsky new Band");
        band4.setCities(Set.of(cityKrakow, cityWroclaw));
        band4.setDescription("Let’s meet the Classical orchestra. It’s the big night: You show up at the concert hall. But holy smokes, there are almost 100 people up on that stage.");
        band4.setMusicGenres(Set.of(genreClassic));
        band4.setSongName("Rococo");
        band4.setSongUrl("http://www.hochmuth.com/mp3/Tchaikovsky_Rococo_Var_orch.mp3");
        band4.setImageUrl("https://images.unsplash.com/photo-1530522070995-aa23c19e77d1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80");

        bandRepository.save(band4);

        var band5 = new Band();
        band5.setName("The Haydn Guys");
        band5.setCities(Set.of(cityGdansk, cityWroclaw));
        band5.setDescription("This fabulous quartet of professional musicians have provided performances for clients such as Manchester City Football Club, Bentley Motors, HSBC and ITV.");
        band5.setMusicGenres(Set.of(genreClassic));
        band5.setSongName("Adiago cover");
        band5.setSongUrl("http://www.hochmuth.com/mp3/Haydn_Adagio.mp3");
        band5.setImageUrl("https://images.unsplash.com/photo-1594122230689-7f659cff55b3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80&fbclid=IwAR3od3IGmT5R1c-U9IKgdmoQAVcM1Qwj7gJRyecOHo2insDepDKx-KE8_Yc");

        bandRepository.save(band5);

        var band6 = new Band();
        band6.setName("Classic Folks");
        band6.setCities(Set.of(cityGdansk, cityWroclaw));
        band6.setDescription("London pianist Mike performs a broad spectrum of styles ranging from classical to contemporary. Perfect for a variety of events such as weddings, drinks parties");
        band6.setMusicGenres(Set.of(genreClassic));
        band6.setSongName("Concerto");
        band6.setSongUrl("http://www.hochmuth.com/mp3/Boccherini_Concerto_478-1.mp3");
        band6.setImageUrl("https://images.unsplash.com/photo-1545239674-02d3da343bd0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1650&q=80&fbclid=IwAR1lnzZigdSrpmBp0GwGQjgaJ-Ta3u9P3XPp5tyTB3pXV0lUWIjV2rp9rDQ");

        bandRepository.save(band6);

        var band = new Band();
        band.setName("Super Duper Rock Band");
        band.setCities(Set.of(cityGdansk, cityWroclaw, cityKrakow));
        band.setDescription("We are rocky band to rock ur bar for free");
        band.setMusicGenres(Set.of(genreRock));
        band.setSongName("YEAHHHH");
        band.setSongUrl("https://files.freemusicarchive.org/storage-freemusicarchive-org/music/Decoder_Magazine/Lately_Kind_of_Yeah/Poindexter/Lately_Kind_of_Yeah_-_06_-_Up_North_Classic.mp3");
        band.setImageUrl("https://i.ytimg.com/vi/23pYfDKyfBk/maxresdefault.jpg");

        bandRepository.save(band);
    }
}
