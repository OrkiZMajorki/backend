package pl.orki.hackathon.webapp.user.boundary;

import java.util.Objects;
import java.util.StringJoiner;

public class UserDTO {

    private String username;
    private String password;
    private String role;
    private String email;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(username, userDTO.username) &&
                Objects.equals(password, userDTO.password) &&
                role == userDTO.role &&
                Objects.equals(email, userDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, email);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserDTO.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("role=" + role)
                .add("email='" + email + "'")
                .toString();
    }
}
