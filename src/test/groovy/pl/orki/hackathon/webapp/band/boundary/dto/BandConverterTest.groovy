package pl.orki.hackathon.webapp.band.boundary.dto

import pl.orki.hackathon.webapp.band.entity.Band
import pl.orki.hackathon.webapp.city.entity.City
import pl.orki.hackathon.webapp.city.entity.CityRepository
import pl.orki.hackathon.webapp.genre.entity.MusicGenre
import pl.orki.hackathon.webapp.genre.entity.MusicGenreRepository
import spock.lang.Specification

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.empty
import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.is

class BandConverterTest extends Specification {

    CityRepository cityRepository = Stub()
    MusicGenreRepository musicGenreRepository = Stub()

    BandConverter bandConverter = new BandConverter(cityRepository, musicGenreRepository)

    def "Should convert Band to BandResponseDTO"() {
        given: "Band entity received from service"
        def band = new Band()
        band.setId(1L)
        band.setName("BandName")
        band.setDescription("BandDescription")
        band.setSongName("Grill u Gawrona")
        band.setSongUrl("http://www.hochmuth.com/mp3/Beethoven_12_Variation.mp3")
        band.setImageUrl("https://images.unsplash.com/photo-1590377830274-93e66ae34415?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80&fbclid=IwAR2NPu6X1t9StmlWzJvRkwLX7-rexrNkRxsFJDgKAl0-h-iNsymdM9EruB4")
        MusicGenre genreRock = createGenre(1L, "Rock")
        band.setMusicGenres(Set.of(genreRock))
        band.setCities(Set.of())

        when: "Client method calls convertToDTO method"
        def dto = bandConverter.convertToDTO(band)

        then: "Result contains all fields from Band entity"
        assertThat dto.getId(), is(band.getId())
        assertThat dto.getName(), is(band.getName())
        assertThat dto.getDescription(), is(band.getDescription())
        assertThat dto.getSongName(), is(band.getSongName())
        assertThat dto.getSongUrl(), is(band.getSongUrl())
        assertThat dto.getImageUrl(), is(band.getImageUrl())
        assertThat dto.getMusicGenres(), hasSize(1)
        assertThat dto.getCities(), empty()
    }

    def "Should convert BandDTO to Band entity"() {
        given: "BandDTO received from controller"
        def dto = new BandDTO()
        dto.setId(1L)
        dto.setName("BandName")
        dto.setDescription("BandDescription")
        dto.setSongName("Grill u Gawrona")
        dto.setSongUrl("http://www.hochmuth.com/mp3/Beethoven_12_Variation.mp3")
        dto.setImageUrl("https://images.unsplash.com/photo-1590377830274-93e66ae34415?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80&fbclid=IwAR2NPu6X1t9StmlWzJvRkwLX7-rexrNkRxsFJDgKAl0-h-iNsymdM9EruB4")
        def musicGenresIds = Set.of(21L)
        dto.setMusicGenresIds(musicGenresIds)
        MusicGenre musicGenre = createGenre(21L, "Rock")
        def musicGenres = List.of(musicGenre)
        musicGenreRepository.findAllById(musicGenresIds) >> musicGenres
        def citiesIds = Set.of(1L)
        dto.setCitiesIds(citiesIds)
        City city = new City()
        city.setId(1L)
        def cities = List.of(city)
        cityRepository.findAllById(citiesIds) >> cities

        when: "Client method calls convertToEntity method"
        def band = bandConverter.convertToEntity(dto)

        then: "Result contains all converted fields from BandDTO"
        band.getId() == dto.getId()
        band.getName() == dto.getName()
        band.getDescription() == dto.getDescription()
        band.getSongName() == dto.getSongName()
        band.getSongUrl() == dto.getSongUrl()
        band.getImageUrl() == dto.getImageUrl()
        band.getMusicGenres() == new HashSet<>(musicGenres);
        band.getCities() == new HashSet<>(cities)
    }

    static MusicGenre createGenre(long id, String name) {
        MusicGenre genre = new MusicGenre()
        genre.setId(id)
        genre.setName(name)

        return genre
    }
}
