package pl.orki.hackathon.webapp.genre.boundary

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import pl.orki.hackathon.webapp.genre.boundary.dto.MusicGenreConverter
import pl.orki.hackathon.webapp.genre.control.MusicGenreService
import spock.lang.Specification

import static org.hamcrest.Matchers.is
import static org.hamcrest.collection.IsCollectionWithSize.hasSize
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static pl.orki.hackathon.webapp.utils.genre.MusicGenreTestUtils.createMusicGenre
import static pl.orki.hackathon.webapp.utils.genre.MusicGenreTestUtils.createMusicGenreResponseDTO

@WebMvcTest(MusicGenreRestController)
class MusicGenreRestControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    private MusicGenreService musicGenreService = Stub()

    @SpringBean
    private MusicGenreConverter musicGenreConverter = Stub()

    def "getMusicGenreById returns 200 and music genre"() {
        given: 'Stubbing musicGenreService and musicGenreConverter'
        def musicGenreId = 25L
        def musicGenreName = "Rock"
        def musicGenre = createMusicGenre(musicGenreId, musicGenreName)
        def musicGenreDTO = createMusicGenreResponseDTO(musicGenreId, musicGenreName)
        musicGenreService.getMusicGenreById(musicGenreId) >> Optional.of(musicGenre)
        musicGenreConverter.convertToResponseDTO(musicGenre) >> musicGenreDTO

        when: 'Client makes request GET /music-genre/25'
        def response = mockMvc.perform(get("/music-genre/{musicGenreId}", musicGenreId))

        then: "Status is OK"
        response.andExpect(status().isOk())

        and: "Response contains musicGenre"
        response.andExpect(MockMvcResultMatchers.jsonPath('$.name', is(musicGenreName)))
        response.andExpect(MockMvcResultMatchers.jsonPath('$.id', is(musicGenreId.intValue())))
    }

    def "getMusicGenreById returns 404 when musicGenre not found"() {
        given: 'Stubbing musicGenreService'
        def musicGenreId = 25L
        musicGenreService.getMusicGenreById(musicGenreId) >> Optional.empty()

        when: 'Client makes request GET /music-genre/25'
        def response = mockMvc.perform(get("/music-genre/{musicGenreId}", musicGenreId))

        then: "Status is Not Found"
        response.andExpect(status().isNotFound())
    }

    def "getMusicGenres returns 200 and all music genres in DTO"() {
        given: 'Stubbing musicGenreService and musicGenreConverter'
        def musicGenreId1 = 25L
        def musicGenreName1 = "Rock"
        def musicGenre1 = createMusicGenre(musicGenreId1, musicGenreName1)
        def musicGenreDTO1 = createMusicGenreResponseDTO(musicGenreId1, musicGenreName1)
        def musicGenreId2 = 37L
        def musicGenreName2 = "Pop"
        def musicGenre2 = createMusicGenre(musicGenreId2, musicGenreName2)
        def musicGenreDTO2 = createMusicGenreResponseDTO(musicGenreId2, musicGenreName2)

        musicGenreService.getMusicGenres() >> List.of(musicGenre1, musicGenre2)
        musicGenreConverter.convertToResponseDTO(musicGenre1) >> musicGenreDTO1
        musicGenreConverter.convertToResponseDTO(musicGenre2) >> musicGenreDTO2

        when: 'Client makes request GET /music-genre'
        def response = mockMvc.perform(get("/music-genre"))

        then: "Status is OK"
        response.andExpect(status().isOk())

        and: "Response contains 2 musicGenres"
        response.andExpect(jsonPath('$', hasSize(2)))
                .andExpect(jsonPath('$[*].name', containsInAnyOrder(musicGenreName1, musicGenreName2)))
                .andExpect(jsonPath('$[*].id', containsInAnyOrder(musicGenreId1.intValue(), musicGenreId2.intValue())))
    }
}
