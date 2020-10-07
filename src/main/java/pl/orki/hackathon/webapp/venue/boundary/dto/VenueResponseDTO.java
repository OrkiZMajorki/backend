package pl.orki.hackathon.webapp.venue.boundary.dto;

import java.util.Objects;
import java.util.Set;

public class VenueResponseDTO {

    private Long id;
    private String name;
    private Integer capacity;
    private Long cityId;
    private String city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        VenueResponseDTO that = (VenueResponseDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(capacity, that.capacity) &&
                Objects.equals(cityId, that.cityId) &&
                Objects.equals(city, that.city) &&
                Objects.equals(musicGenres, that.musicGenres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, cityId, city, musicGenres);
    }

    @Override
    public String toString() {
        return "VenueResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", cityId=" + cityId +
                ", city='" + city + '\'' +
                ", musicGenres=" + musicGenres +
                '}';
    }
}
