package pl.orki.hackathon.webapp.band.boundary.dto

import pl.orki.hackathon.webapp.band.entity.Band
import pl.orki.hackathon.webapp.genre.MusicGenre
import spock.lang.Specification

class BandConverterTest extends Specification {

    def bandConverter = new BandConverter()

    def "Should convert Band to BandResponseDTO" () {
        given: "Band entity received from service"
        def band = new Band()
        band.setId(1L)
        band.setName("BandName")
        band.setDescription("BandDescription")
        band.setSongName("Grill u Gawrona")
        band.setSongUrl("http://www.hochmuth.com/mp3/Beethoven_12_Variation.mp3")
        band.setImageUrl("https://images.unsplash.com/photo-1590377830274-93e66ae34415?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80&fbclid=IwAR2NPu6X1t9StmlWzJvRkwLX7-rexrNkRxsFJDgKAl0-h-iNsymdM9EruB4")
        band.setMusicGenres(Set.of(MusicGenre.ROCK))
        band.setCities(Set.of())

        when: "Client method calls convertToDTO method"
        def dto = bandConverter.convertToDTO(band)

        then: "Result contains all fields from Band entity"
        dto.getId() == band.getId()
        dto.getName() == band.getName()
        dto.getDescription() == band.getDescription()
        dto.getSongName() == band.getSongName()
        dto.getSongUrl() == band.getSongUrl()
        dto.getImageUrl() == band.getImageUrl()
        dto.getMusicGenres().size() == 1
        dto.getCities().isEmpty()
    }
}
