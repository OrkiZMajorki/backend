package pl.orki.hackathon.webapp.venue.boundary;

import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

public class VenueDTO {

    private Long id;
    private String name;
    private Integer capacity;
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
        VenueDTO venueDTO = (VenueDTO) o;
        return Objects.equals(id, venueDTO.id) &&
                Objects.equals(name, venueDTO.name) &&
                Objects.equals(capacity, venueDTO.capacity) &&
                Objects.equals(city, venueDTO.city) &&
                Objects.equals(musicGenres, venueDTO.musicGenres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, city, musicGenres);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VenueDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("capacity=" + capacity)
                .add("city='" + city + "'")
                .add("musicGenres=" + musicGenres)
                .toString();
    }
}
