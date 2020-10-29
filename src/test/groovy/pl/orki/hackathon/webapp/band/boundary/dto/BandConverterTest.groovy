package pl.orki.hackathon.webapp.band.boundary.dto


import pl.orki.hackathon.webapp.band.entity.Band
import pl.orki.hackathon.webapp.city.entity.City
import pl.orki.hackathon.webapp.city.entity.CityRepository
import pl.orki.hackathon.webapp.genre.MusicGenre
import spock.lang.Specification

class BandConverterTest extends Specification {

    CityRepository cityRepository = Stub()

    BandConverter bandConverter = new BandConverter(cityRepository)

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

    def "Should convert BandDTO to Band entity" () {
        given: "BandDTO received from controller"
        def dto = new BandDTO()
        dto.setId(1L)
        dto.setName("BandName")
        dto.setDescription("BandDescription")
        dto.setSongName("Grill u Gawrona")
        dto.setSongUrl("http://www.hochmuth.com/mp3/Beethoven_12_Variation.mp3")
        dto.setImageUrl("https://images.unsplash.com/photo-1590377830274-93e66ae34415?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80&fbclid=IwAR2NPu6X1t9StmlWzJvRkwLX7-rexrNkRxsFJDgKAl0-h-iNsymdM9EruB4")
        dto.setMusicGenres(Set.of(MusicGenre.ROCK.toString()))
        def citiesIds = Set.of(1L)
        dto.setCitiesIds(citiesIds)
        City city = new City()
        city.setId(2L)
        def cities = List.of(new City())
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
        band.getMusicGenres().size() == 1
        band.getCities() == new HashSet<>(cities)
    }
}
