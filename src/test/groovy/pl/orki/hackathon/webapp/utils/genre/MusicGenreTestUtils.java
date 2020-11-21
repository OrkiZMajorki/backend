package pl.orki.hackathon.webapp.utils.genre;

import pl.orki.hackathon.webapp.genre.boundary.dto.MusicGenreResponseDTO;
import pl.orki.hackathon.webapp.genre.entity.MusicGenre;

public class MusicGenreTestUtils {

    private MusicGenreTestUtils() {
    }

    public static MusicGenre createMusicGenre(long id, String name) {
        var musicGenre = new MusicGenre();
        musicGenre.setId(id);
        musicGenre.setName(name);

        return musicGenre;
    }

    public static MusicGenreResponseDTO createMusicGenreResponseDTO(long id, String name) {
        var musicGenreResponseDTO = new MusicGenreResponseDTO();
        musicGenreResponseDTO.setId(id);
        musicGenreResponseDTO.setName(name);

        return musicGenreResponseDTO;
    }
}
