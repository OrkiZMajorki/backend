package pl.orki.hackathon.webapp.genre.boundary.dto;

import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.genre.entity.MusicGenre;

@Component
public class MusicGenreConverter {

    public MusicGenreResponseDTO convertToResponseDTO(MusicGenre musicGenre) {
        var dto = new MusicGenreResponseDTO();
        dto.setId(musicGenre.getId());
        dto.setName(musicGenre.getName());

        return dto;
    }
}
