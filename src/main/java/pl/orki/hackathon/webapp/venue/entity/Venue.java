package pl.orki.hackathon.webapp.venue.entity;

import pl.orki.hackathon.webapp.city.City;
import pl.orki.hackathon.webapp.genre.MusicGenre;
import pl.orki.hackathon.webapp.user.entity.User;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "venue")
public class Venue {

    @Id
    @GeneratedValue(generator = "venue_generator")
    @SequenceGenerator(
            name = "venue_generator",
            sequenceName = "venue_sequence"
    )
    @Column(name = "venue_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    private City city;

    @Enumerated(EnumType.STRING)
    @Column(name = "music_genre")
    @ElementCollection(targetClass = MusicGenre.class)
    @CollectionTable(name="venue_genre")
    private Set<MusicGenre> musicGenre;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<MusicGenre> getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(Set<MusicGenre> musicGenre) {
        this.musicGenre = musicGenre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue venue = (Venue) o;
        return Objects.equals(id, venue.id) &&
                Objects.equals(name, venue.name) &&
                Objects.equals(capacity, venue.capacity) &&
                city == venue.city &&
                Objects.equals(musicGenre, venue.musicGenre) &&
                Objects.equals(user, venue.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, city, musicGenre, user);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Venue.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("capacity=" + capacity)
                .add("city=" + city)
                .add("musicGenre=" + musicGenre)
                .add("user=" + user)
                .toString();
    }
}