package pl.orki.hackathon.webapp.city.boundary

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import pl.orki.hackathon.webapp.city.boundary.dto.CityConverter
import pl.orki.hackathon.webapp.city.boundary.dto.CityResponseDTO
import pl.orki.hackathon.webapp.city.control.CityService
import pl.orki.hackathon.webapp.city.entity.City
import spock.lang.Specification

import static org.hamcrest.collection.IsCollectionWithSize.hasSize
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CityRestController)
class CityRestControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    private CityService cityService = Stub()

    @SpringBean
    private CityConverter cityConverter = Stub()

    def "getCities returns all cities in DTO"() {
        given: 'Stubbing cityService and cityConverter'
        def city1 = new City()
        def cityName1 = "city1"
        city1.setName(cityName1)
        def cityDTO1 = new CityResponseDTO();
        cityDTO1.setName(cityName1)
        def city2 = new City()
        def cityName2 = "city2"
        city2.setName(cityName2)
        def cityDTO2 = new CityResponseDTO()
        cityDTO2.setName(cityName2)

        cityService.getCities() >> List.of(city1, city2)
        cityConverter.convertToResponseDTO(city1) >> cityDTO1
        cityConverter.convertToResponseDTO(city2) >> cityDTO2

        when: 'Client makes request GET /city'
        def response = mockMvc.perform(get("/city"))

        then: "Status is OK"
        response.andExpect(status().isOk())

        and: "Response contains 2 cities"
        response.andExpect(jsonPath('$', hasSize(2)))
                .andExpect(jsonPath('$[*].name', containsInAnyOrder(cityName1, cityName2)))
    }
}
