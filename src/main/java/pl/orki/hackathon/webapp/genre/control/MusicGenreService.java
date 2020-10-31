package pl.orki.hackathon.webapp.genre.control;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.genre.entity.MusicGenre;
import pl.orki.hackathon.webapp.genre.entity.MusicGenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MusicGenreService {

    private final MusicGenreRepository musicGenreRepository;

    public MusicGenreService(MusicGenreRepository musicGenreRepository) {
        this.musicGenreRepository = musicGenreRepository;
    }

    @Transactional(readOnly = true)
    public Optional<MusicGenre> getMusicGenreById(Long id) {
        return musicGenreRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<MusicGenre> getMusicGenres() {
        return musicGenreRepository.findAll();
    }
}
