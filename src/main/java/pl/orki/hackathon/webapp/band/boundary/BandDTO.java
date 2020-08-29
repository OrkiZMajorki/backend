package pl.orki.hackathon.webapp.band.boundary;

import pl.orki.hackathon.webapp.city.City;
import pl.orki.hackathon.webapp.genre.MusicGenre;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

public class BandDTO {
    private String name;
    private String description;
    private String songUrl;
    private String imageUrl;
    private Set<City> city;
    private Set<MusicGenre> musicGenres;

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

    public Set<MusicGenre> getMusicGenres() {
        return musicGenres;
    }

    public void setMusicGenres(Set<MusicGenre> musicGenres) {
        this.musicGenres = musicGenres;
    }
}
