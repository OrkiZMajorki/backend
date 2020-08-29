package pl.orki.hackathon.webapp.band.entity;

import pl.orki.hackathon.webapp.city.City;
import pl.orki.hackathon.webapp.genre.MusicGenre;

import javax.persistence.*;
import java.util.Objects;
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
    private City city;

    @Enumerated(EnumType.STRING)
    @Column(name = "music_genre")
    private MusicGenre musicGenre;

//    private User user;


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "client_product",
//            joinColumns = @JoinColumn(name = "client_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private Set<Product> products;


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

    public MusicGenre getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(MusicGenre musicGenre) {
        this.musicGenre = musicGenre;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
                musicGenre == band.musicGenre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, songUrl, imageUrl, city, musicGenre);
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
                .toString();
    }
}
