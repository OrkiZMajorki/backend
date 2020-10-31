package pl.orki.hackathon.webapp.band.control

import pl.orki.hackathon.webapp.band.entity.BandRepository
import pl.orki.hackathon.webapp.city.entity.City
import pl.orki.hackathon.webapp.city.entity.CityRepository

import pl.orki.hackathon.webapp.genre.entity.MusicGenre
import pl.orki.hackathon.webapp.genre.entity.MusicGenreRepository
import spock.lang.Specification

class BandServiceTest extends Specification {

    def "getBandsByMusicGenresAndCities should invoke bandRepository exactly once"() {
        given: "genres names and cities ids received from client method"
        def musicGenresIds = List.of(14L)
        def citiesIds = List.of(1L)
        def city = new City()
        city.setName("Test city")
        def musicGenre = new MusicGenre()
        musicGenre.setName("Rock")
        BandRepository bandRepository = Mock()
        CityRepository cityRepository = Stub()
        cityRepository.findAllById(citiesIds) >> List.of(city)
        MusicGenreRepository musicGenreRepository = Stub()
        musicGenreRepository.findAllById(musicGenresIds) >> List.of(musicGenre)
        BandService bandService = new BandService(bandRepository, cityRepository, musicGenreRepository)

        when: "getBandsByMusicGenresAndCities is called"
        bandService.getBandsByMusicGenresAndCities(musicGenresIds, citiesIds)

        then: "bandRepository.findAllByMusicGenresInAndCitiesIn is called exactly once"
        1 * bandRepository.findAllByMusicGenresInAndCitiesIn({it.contains(musicGenre)}, {it.contains(city)})
    }
}
