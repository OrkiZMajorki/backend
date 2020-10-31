package pl.orki.hackathon.webapp.user.boundary

import pl.orki.hackathon.webapp.band.boundary.dto.BandResponseDTO
import pl.orki.hackathon.webapp.user.entity.User
import spock.lang.Specification

class UserConverterTest extends Specification {

    def userConverter = new UserConverter()

    def "Should convert User with Band to UserResponseDTO"() {
        given: "User and BandResponseDTO received from service"

        def user = new User()
        user.setEmail("test@test")
        user.setUsername("test")
        def bandResponseDTO = createBandResponseDTO()

        when: "Client method calls convertToResponseDTOWithBand method"
        def responseDTO = userConverter.convertToResponseDTOWithBand(user, bandResponseDTO)

        then: "Result contains all fields from Band entity"
        responseDTO.getBand() == bandResponseDTO
        responseDTO.getEmail() == user.getEmail()
        responseDTO.getUsername() == user.getUsername()
    }

    private static BandResponseDTO createBandResponseDTO() {
        def dto = new BandResponseDTO()
        dto.setId(1L)
        dto.setName("BandName")
        dto.setDescription("BandDescription")
        dto.setSongName("Grill u Gawrona")
        dto.setSongUrl("http://www.hochmuth.com/mp3/Beethoven_12_Variation.mp3")
        dto.setImageUrl("https://images.unsplash.com/photo-1590377830274-93e66ae34415?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80&fbclid=IwAR2NPu6X1t9StmlWzJvRkwLX7-rexrNkRxsFJDgKAl0-h-iNsymdM9EruB4")
        dto.setMusicGenres(Set.of())
        dto.setCities(Set.of())
        return dto;
    }
}
