package pl.orki.hackathon.webapp.genre.control

import pl.orki.hackathon.webapp.genre.entity.MusicGenreRepository
import spock.lang.Specification

class MusicGenreServiceTest extends Specification {

    def "getMusicGenreById should invoke musicGenreRepository exactly once"() {
        given: "id received from client method"
        def musicGenreId = 13L
        MusicGenreRepository musicGenreRepository = Mock()
        MusicGenreService musicGenreService = new MusicGenreService(musicGenreRepository)

        when: "getMusicGenreById is called"
        musicGenreService.getMusicGenreById(musicGenreId)

        then: "musicGenreRepository.findById is called exactly once"
        1 * musicGenreRepository.findById(musicGenreId)
    }

    def "getMusicGenres should invoke musicGenreRepository exactly once"() {
        given: "mocking musicGenreRepository"
        MusicGenreRepository musicGenreRepository = Mock()
        MusicGenreService musicGenreService = new MusicGenreService(musicGenreRepository)

        when: "getMusicGenres is called"
        musicGenreService.getMusicGenres()

        then: "musicGenreRepository.findAll is called exactly once"
        1 * musicGenreRepository.findAll()
    }
}
