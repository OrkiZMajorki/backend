package pl.orki.hackathon.webapp.genre.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicGenreRepository extends JpaRepository<MusicGenre, Long> {
}
