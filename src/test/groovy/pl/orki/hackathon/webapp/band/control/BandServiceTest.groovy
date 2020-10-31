package pl.orki.hackathon.webapp.band.control

import pl.orki.hackathon.webapp.band.entity.BandRepository
import pl.orki.hackathon.webapp.city.entity.City
import pl.orki.hackathon.webapp.city.entity.CityRepository

import pl.orki.hackathon.webapp.user.entity.UserRepository
import spock.lang.Specification

class BandServiceTest extends Specification {

    def "getBandsByMusicGenresAndCities should invoke bandRepository exactly once"() {
        given: "genres names and cities ids received from client method"
        def musicGenresNames = List.of(MusicGenre.ROCK.toString())
        def citiesIds = List.of(1L)
        def city = new City()
        city.setName("Test city")
        BandRepository bandRepository = Mock()
        UserRepository userRepository = Mock()
        CityRepository cityRepository = Stub()
        cityRepository.findAllById(citiesIds) >> List.of(city)
        BandService bandService = new BandService(bandRepository, userRepository, cityRepository)

        when: "getBandsByMusicGenresAndCities is called"
        bandService.getBandsByMusicGenresAndCities(musicGenresNames, citiesIds)

        then: "bandRepository.findAllByMusicGenresInAndCitiesIn is called exactly once"
        1 * bandRepository.findAllByMusicGenresInAndCitiesIn({it.contains(MusicGenre.ROCK)}, {it.contains(city)})
    }
}
