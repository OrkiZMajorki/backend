package pl.orki.hackathon.webapp.band.entity;

import pl.orki.hackathon.webapp.city.entity.City;
import pl.orki.hackathon.webapp.genre.MusicGenre;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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

    @Column(name = "song_name")
    private String songName;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "band_city",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id")
    )
    private Set<City> cities;

    @Enumerated(EnumType.STRING)
    @Column(name = "music_genre")
    @ElementCollection(targetClass = MusicGenre.class)
    @CollectionTable(name="band_genre")
    private Set<MusicGenre> musicGenres;

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

    public Set<MusicGenre> getMusicGenres() {
        return musicGenres;
    }

    public void setMusicGenres(Set<MusicGenre> musicGenre) {
        this.musicGenres = musicGenre;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
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
                Objects.equals(songName, band.songName) &&
                Objects.equals(imageUrl, band.imageUrl) &&
                Objects.equals(cities, band.cities) &&
                Objects.equals(musicGenres, band.musicGenres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, songUrl, songName, imageUrl, cities, musicGenres);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Band.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("songUrl='" + songUrl + "'")
                .add("songName='" + songName + "'")
                .add("imageUrl='" + imageUrl + "'")
                .add("cities=" + cities)
                .add("musicGenres=" + musicGenres)
                .toString();
    }
}
