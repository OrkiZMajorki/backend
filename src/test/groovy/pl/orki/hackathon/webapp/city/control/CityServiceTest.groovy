package pl.orki.hackathon.webapp.city.control

import pl.orki.hackathon.webapp.city.entity.CityRepository
import spock.lang.Specification

class CityServiceTest extends Specification {

    def "getCityById should invoke cityRepository exactly once"() {
        given: "id received from client method"
        def cityId = 13L
        CityRepository cityRepository = Mock()
        CityService cityService = new CityService(cityRepository)

        when: "getCityById is called"
        cityService.getCityById(cityId)

        then: "cityRepository.findById is called exactly once"
        1 * cityRepository.findById(cityId)
    }

    def "getCities should invoke cityRepository exactly once"() {
        given: "mocking cityRepository"
        CityRepository cityRepository = Mock()
        CityService cityService = new CityService(cityRepository)

        when: "getCities is called"
        cityService.getCities()

        then: "cityRepository.findAll is called exactly once"
        1 * cityRepository.findAll()
    }
}
