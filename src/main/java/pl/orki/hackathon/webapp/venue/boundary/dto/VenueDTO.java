package pl.orki.hackathon.webapp.venue.boundary.dto;

import java.util.Objects;
import java.util.Set;

public class VenueDTO {

    private Long id;
    private String name;
    private Integer capacity;
    private Long cityId;
    private Set<String> musicGenres;

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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Set<String> getMusicGenres() {
        return musicGenres;
    }

    public void setMusicGenres(Set<String> musicGenres) {
        this.musicGenres = musicGenres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VenueDTO venueDTO = (VenueDTO) o;
        return Objects.equals(id, venueDTO.id) &&
                Objects.equals(name, venueDTO.name) &&
                Objects.equals(capacity, venueDTO.capacity) &&
                Objects.equals(cityId, venueDTO.cityId) &&
                Objects.equals(musicGenres, venueDTO.musicGenres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, cityId, musicGenres);
    }

    @Override
    public String toString() {
        return "VenueDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", cityId=" + cityId +
                ", musicGenres=" + musicGenres +
                '}';
    }
}
