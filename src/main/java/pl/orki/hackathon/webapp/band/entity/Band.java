package pl.orki.hackathon.webapp.band.entity;

import pl.orki.hackathon.webapp.city.City;
import pl.orki.hackathon.webapp.genre.MusicGenre;
import pl.orki.hackathon.webapp.user.entity.User;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "band")
public class Band {

    @Id
    @GeneratedValue(generator = "course_generator")
    @SequenceGenerator(
            name = "course_generator",
            sequenceName = "course_sequence"
    )
    @Column(name = "band_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "song_url")
    private String songUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    @ElementCollection(targetClass = City.class)
    @CollectionTable(name="band_city")
    private Set<City> city;

    @Enumerated(EnumType.STRING)
    @Column(name = "music_genre")
    @ElementCollection(targetClass = MusicGenre.class)
    @CollectionTable(name="band_genre")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<City> getCity() {
        return city;
    }

    public void setCity(Set<City> city) {
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
        Band band = (Band) o;
        return Objects.equals(id, band.id) &&
                Objects.equals(name, band.name) &&
                Objects.equals(description, band.description) &&
                Objects.equals(songUrl, band.songUrl) &&
                Objects.equals(imageUrl, band.imageUrl) &&
                city == band.city &&
                musicGenre == band.musicGenre &&
                Objects.equals(user, band.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, songUrl, imageUrl, city, musicGenre, user);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Band.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("songUrl='" + songUrl + "'")
                .add("imageUrl='" + imageUrl + "'")
                .add("city=" + city)
                .add("musicGenre=" + musicGenre)
                .add("user=" + user)
                .toString();
    }
}
