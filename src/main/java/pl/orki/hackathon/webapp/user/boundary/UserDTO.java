package pl.orki.hackathon.webapp.user.boundary;

import pl.orki.hackathon.webapp.band.boundary.dto.BandDTO;
import pl.orki.hackathon.webapp.venue.boundary.dto.VenueDTO;

import java.util.Objects;

public class UserDTO {

    private String username;
    private String password;
    private String email;
    private BandDTO band;
    private VenueDTO venue;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BandDTO getBand() {
        return band;
    }

    public void setBand(BandDTO band) {
        this.band = band;
    }

    public VenueDTO getVenue() {
        return venue;
    }

    public void setVenue(VenueDTO venue) {
        this.venue = venue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(username, userDTO.username) &&
                Objects.equals(password, userDTO.password) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(band, userDTO.band) &&
                Objects.equals(venue, userDTO.venue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, band, venue);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", band=" + band +
                ", venue=" + venue +
                '}';
    }
}
