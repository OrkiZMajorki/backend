package pl.orki.hackathon.webapp.genre.entity;

import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.venue.entity.Venue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Entity
public class MusicGenre {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "musicGenres")
    private Set<Band> bands;

    @ManyToMany(mappedBy = "musicGenres")
    private Set<Venue> venues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Band> getBands() {
        return bands;
    }

    public void setBands(Set<Band> bands) {
        this.bands = bands;
    }

    public Set<Venue> getVenues() {
        return venues;
    }

    public void setVenues(Set<Venue> venues) {
        this.venues = venues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicGenre musicGenre = (MusicGenre) o;
        return Objects.equals(id, musicGenre.id) &&
                Objects.equals(name, musicGenre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MusicGenre.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
