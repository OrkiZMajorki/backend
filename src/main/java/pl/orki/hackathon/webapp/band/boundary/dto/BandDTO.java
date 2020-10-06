package pl.orki.hackathon.webapp.band.boundary.dto;


import pl.orki.hackathon.webapp.user.entity.User;

import java.util.Objects;
import java.util.Set;

public class BandDTO {

    private Long id;
    private String name;
    private String description;
    private String songUrl;
    private String imageUrl;
    private Set<Long> citiesIds;
    private Set<String> musicGenres;
    private String songName;
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

    public Set<Long> getCitiesIds() {
        return citiesIds;
    }

    public void setCitiesIds(Set<Long> citiesIds) {
        this.citiesIds = citiesIds;
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
        BandDTO bandDTO = (BandDTO) o;
        return Objects.equals(id, bandDTO.id) &&
                Objects.equals(name, bandDTO.name) &&
                Objects.equals(description, bandDTO.description) &&
                Objects.equals(songUrl, bandDTO.songUrl) &&
                Objects.equals(imageUrl, bandDTO.imageUrl) &&
                Objects.equals(citiesIds, bandDTO.citiesIds) &&
                Objects.equals(musicGenres, bandDTO.musicGenres) &&
                Objects.equals(songName, bandDTO.songName) &&
                Objects.equals(user, bandDTO.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, songUrl, imageUrl, citiesIds, musicGenres, songName, user);
    }

    @Override
    public String toString() {
        return "BandDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", songUrl='" + songUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", citiesIds=" + citiesIds +
                ", musicGenres=" + musicGenres +
                ", songName='" + songName + '\'' +
                ", userDTO=" + user +
                '}';
    }
}
