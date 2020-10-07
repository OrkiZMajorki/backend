package pl.orki.hackathon.webapp.band.boundary.dto;

import java.util.Objects;
import java.util.Set;

public class BandResponseDTO {

    private Long id;
    private String name;
    private String description;
    private String songUrl;
    private String imageUrl;
    private Set<Long> citiesIds;
    private Set<String> cities;
    private Set<String> musicGenres;
    private String songName;

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

    public Set<Long> getCitiesIds() {
        return citiesIds;
    }

    public void setCitiesIds(Set<Long> citiesIds) {
        this.citiesIds = citiesIds;
    }

    public Set<String> getCities() {
        return cities;
    }

    public void setCities(Set<String> cities) {
        this.cities = cities;
    }

    public Set<String> getMusicGenres() {
        return musicGenres;
    }

    public void setMusicGenres(Set<String> musicGenres) {
        this.musicGenres = musicGenres;
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
        BandResponseDTO that = (BandResponseDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(songUrl, that.songUrl) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(citiesIds, that.citiesIds) &&
                Objects.equals(cities, that.cities) &&
                Objects.equals(musicGenres, that.musicGenres) &&
                Objects.equals(songName, that.songName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, songUrl, imageUrl, citiesIds, cities, musicGenres, songName);
    }

    @Override
    public String toString() {
        return "BandResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", songUrl='" + songUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", citiesIds=" + citiesIds +
                ", cities=" + cities +
                ", musicGenres=" + musicGenres +
                ", songName='" + songName + '\'' +
                '}';
    }
}
