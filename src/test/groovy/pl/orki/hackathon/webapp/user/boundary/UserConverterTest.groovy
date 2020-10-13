package pl.orki.hackathon.webapp.user.boundary

import pl.orki.hackathon.webapp.band.boundary.dto.BandConverter
import pl.orki.hackathon.webapp.band.entity.Band
import pl.orki.hackathon.webapp.genre.MusicGenre
import pl.orki.hackathon.webapp.user.entity.User
import spock.lang.Specification

class UserConverterTest extends Specification {

    def userConverter = new UserConverter()
    def bandConverter = new BandConverter()

    def "Should convert User with Band to UserResponseDTO"() {
        given: "User and BandResponseDTO received from service"

        def user = new User()
        user.setEmail("test@test")
        user.setUsername("test")

        def bandResponseDTO = bandConverter.convertToDTO(createBand())

        when: "Client method calls convertToResponseDTOWithBand method"
        def responseDTO = userConverter.convertToResponseDTOWithBand(user, bandResponseDTO)

        then: "Result contains all fields from Band entity"
        responseDTO.getBand() == bandResponseDTO
        responseDTO.getEmail() == user.getEmail()
        responseDTO.getUsername() == user.getUsername()

    }

    private static Band createBand() {
        def band = new Band()
        band.setId(1L)
        band.setName("BandName")
        band.setDescription("BandDescription")
        band.setSongName("Grill u Gawrona")
        band.setSongUrl("http://www.hochmuth.com/mp3/Beethoven_12_Variation.mp3")
        band.setImageUrl("https://images.unsplash.com/photo-1590377830274-93e66ae34415?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80&fbclid=IwAR2NPu6X1t9StmlWzJvRkwLX7-rexrNkRxsFJDgKAl0-h-iNsymdM9EruB4")
        band.setMusicGenres(Set.of(MusicGenre.ROCK))
        band.setCities(Set.of())
        return band;
    }


}
