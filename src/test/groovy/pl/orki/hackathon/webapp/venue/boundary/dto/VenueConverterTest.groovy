package pl.orki.hackathon.webapp.venue.boundary.dto

import pl.orki.hackathon.webapp.city.entity.City
import pl.orki.hackathon.webapp.city.entity.CityRepository
import pl.orki.hackathon.webapp.genre.entity.MusicGenre
import pl.orki.hackathon.webapp.genre.entity.MusicGenreRepository
import pl.orki.hackathon.webapp.venue.entity.Venue
import spock.lang.Specification

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.contains
import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.is

class VenueConverterTest extends Specification {

    CityRepository cityRepository = Stub()
    MusicGenreRepository musicGenreRepository = Stub()

    VenueConverter venueConverter = new VenueConverter(cityRepository, musicGenreRepository)

    def "Should convert Venue to VenueResponseDTO"() {
        given: "Venue entity received from service"
        Venue venue = new Venue()
        venue.setId(1L)
        venue.setName("VenueName")
        venue.setCapacity(20)
        City city = createCity(43L, "Zbąszynek")
        venue.setCity(city)
        MusicGenre genreRock = createGenre(1L, "Rock")
        venue.setMusicGenres(Set.of(genreRock))

        when: "Client method calls convertToDTO method"
        def dto = venueConverter.convertToDTO(venue)

        then: "Result contains all fields from Venue entity"
        assertThat dto.getId(), is(venue.getId())
        assertThat dto.getName(), is(venue.getName())
        assertThat dto.getCapacity(), is(venue.getCapacity())
        assertThat dto.getCity(), is(city.getName())
        assertThat dto.getCityId(), is(city.getId())
        assertThat dto.getMusicGenres(), hasSize(1)
        assertThat dto.getMusicGenres(), contains(genreRock.getName())
        assertThat dto.getMusicGenresIds(), hasSize(1)
        assertThat dto.getMusicGenresIds(), contains(genreRock.getId())
    }

    def "Should convert VenueDTO to Venue entity"() {
        given: "VenueDTO received from controller"
        def dto = new VenueDTO()
        dto.setId(1L)
        dto.setName("VenueName")
        dto.setCapacity(666)
        def cityId = 237L
        dto.setCityId(cityId)
        City city = createCity(cityId, "Zabrze")
        cityRepository.findById(cityId) >> Optional.of(city)
        def genreId = 21L
        def musicGenresIds = Set.of(genreId)
        dto.setMusicGenresIds(musicGenresIds)
        MusicGenre musicGenre = createGenre(genreId, "Rock")
        def musicGenres = List.of(musicGenre)
        musicGenreRepository.findAllById(musicGenresIds) >> musicGenres

        when: "Client method calls convertToEntity method"
        def venue = venueConverter.convertToEntity(dto)

        then: "Result contains all converted fields from VenueDTO"
        assertThat venue.getId(), is(dto.getId())
        assertThat venue.getName(), is(dto.getName())
        assertThat venue.getCapacity(), is(dto.getCapacity())
        assertThat venue.getCity(), is(city)
        assertThat venue.getMusicGenres(), hasSize(1)
        assertThat venue.getMusicGenres(), contains(musicGenre)
    }

    static City createCity(long id, String name) {
        City city = new City()
        city.setId(id)
        city.setName(name)

        return city
    }

    static MusicGenre createGenre(long id, String name) {
        MusicGenre genre = new MusicGenre()
        genre.setId(id)
        genre.setName(name)

        return genre
    }
}
