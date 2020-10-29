package pl.orki.hackathon.webapp.city.boundary.dto

import pl.orki.hackathon.webapp.city.entity.City
import spock.lang.Specification

class CityConverterTest extends Specification {

    CityConverter cityConverter = new CityConverter()

    def "Should convert to CityResponseDTO"() {
        given: "Received City entity"
        City city = new City()
        def cityId = 13L
        city.setId(cityId)
        def cityName = "Zabrze"
        city.setName(cityName)

        when: "Client method calls convertToResponseDTO method"
        CityResponseDTO dto = cityConverter.convertToResponseDTO(city)

        then: "Fields from City entity are converted to CityResponseDTO"
        dto.getId() == cityId
        dto.getName() == cityName
    }
}
