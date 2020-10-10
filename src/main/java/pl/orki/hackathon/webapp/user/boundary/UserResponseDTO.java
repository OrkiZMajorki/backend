package pl.orki.hackathon.webapp.user.boundary;

import pl.orki.hackathon.webapp.band.boundary.dto.BandResponseDTO;
import pl.orki.hackathon.webapp.venue.boundary.dto.VenueResponseDTO;

import java.util.Objects;
import java.util.StringJoiner;

public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private BandResponseDTO band;
    private VenueResponseDTO venue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BandResponseDTO getBand() {
        return band;
    }

    public void setBand(BandResponseDTO band) {
        this.band = band;
    }

    public VenueResponseDTO getVenue() {
        return venue;
    }

    public void setVenue(VenueResponseDTO venue) {
        this.venue = venue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDTO that = (UserResponseDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(band, that.band) &&
                Objects.equals(venue, that.venue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, band, venue);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserResponseDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username='" + username + "'")
                .add("email='" + email + "'")
                .add("band=" + band)
                .add("venue=" + venue)
                .toString();
    }
}
