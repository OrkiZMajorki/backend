package pl.orki.hackathon.webapp.genre.boundary.dto

import pl.orki.hackathon.webapp.genre.entity.MusicGenre
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat
import static pl.orki.hackathon.webapp.utils.genre.MusicGenreTestUtils.createMusicGenre

class MusicGenreConverterTest extends Specification {

    MusicGenreConverter musicGenreConverter = new MusicGenreConverter()

    def "Should convert to MusicGenreResponseDTO"() {
        given: "Received MusicGenre entity"
        def musicGenreId = 13L
        def musicGenreName = "Zabrze"
        MusicGenre musicGenre = createMusicGenre(musicGenreId, musicGenreName)

        when: "Client method calls convertToResponseDTO method"
        MusicGenreResponseDTO dto = musicGenreConverter.convertToResponseDTO(musicGenre)

        then: "Fields from MusicGenre entity are converted to MusicGenreResponseDTO"
        assertThat dto.getId(), is(musicGenreId)
        assertThat dto.getName(), is(musicGenreName)
    }
}
