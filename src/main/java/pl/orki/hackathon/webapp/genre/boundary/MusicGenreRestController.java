package pl.orki.hackathon.webapp.genre.boundary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.orki.hackathon.webapp.genre.boundary.dto.MusicGenreConverter;
import pl.orki.hackathon.webapp.genre.boundary.dto.MusicGenreResponseDTO;
import pl.orki.hackathon.webapp.genre.control.MusicGenreService;
import pl.orki.hackathon.webapp.genre.entity.MusicGenre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/music-genre")
public class MusicGenreRestController {

    private final MusicGenreService musicGenreService;
    private final MusicGenreConverter musicGenreConverter;

    public MusicGenreRestController(MusicGenreService musicGenreService, MusicGenreConverter musicGenreConverter) {
        this.musicGenreService = musicGenreService;
        this.musicGenreConverter = musicGenreConverter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicGenreResponseDTO> getMusicGenreById(@PathVariable Long id) {
        Optional<MusicGenreResponseDTO> musicGenreDTO = musicGenreService.getMusicGenreById(id)
                .map(musicGenreConverter::convertToResponseDTO);

        return ResponseEntity.of(musicGenreDTO);
    }

    @GetMapping
    public ResponseEntity<List<MusicGenreResponseDTO>> getMusicGenres() {
        List<MusicGenre> musicGenres = musicGenreService.getMusicGenres();
        List<MusicGenreResponseDTO> musicGenresDTO = musicGenres.stream()
                .map(musicGenreConverter::convertToResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(musicGenresDTO);
    }
}
