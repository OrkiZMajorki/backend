package pl.orki.hackathon.webapp.user.control;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.band.boundary.dto.BandConverter;
import pl.orki.hackathon.webapp.band.boundary.dto.BandDTO;
import pl.orki.hackathon.webapp.band.control.BandService;
import pl.orki.hackathon.webapp.band.entity.Band;
import pl.orki.hackathon.webapp.user.boundary.UserDTO;
import pl.orki.hackathon.webapp.user.boundary.UserLoginDTO;
import pl.orki.hackathon.webapp.user.boundary.UserResponseDTO;
import pl.orki.hackathon.webapp.user.entity.User;
import pl.orki.hackathon.webapp.user.entity.UserRepository;
import pl.orki.hackathon.webapp.venue.boundary.dto.VenueConverter;
import pl.orki.hackathon.webapp.venue.boundary.dto.VenueDTO;
import pl.orki.hackathon.webapp.venue.control.VenueService;
import pl.orki.hackathon.webapp.venue.entity.Venue;

import java.util.Optional;

import static pl.orki.hackathon.webapp.user.boundary.UserConverter.*;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final BandService bandService;
    private final VenueService venueService;
    private final BandConverter bandConverter;
    private final VenueConverter venueConverter;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, BandService bandService, BandService bandService1, BandConverter bandConverter, VenueService venueService, VenueService venueService1, VenueConverter venueConverter) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.bandService = bandService1;
        this.bandConverter = bandConverter;
        this.venueService = venueService1;
        this.venueConverter = venueConverter;
    }

    @Transactional(readOnly = true)
    public Optional<User> authenticateUser(UserLoginDTO userLoginDTO) {
        return userRepository.findByEmail(userLoginDTO.getEmail())
                .filter(user -> bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), user.getPassword()));
    }

    @Transactional
    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserResponseDTO createUserBand(UserDTO userDTO, BandDTO bandDTO) {
        User user = convertToEntity(userDTO);
        Band band = bandService.createBand(bandConverter.convertToEntity(bandDTO));
        user.setBand(band);
        createUser(user);
        return convertToResponseDTOWithBand(user, bandConverter.convertToDTO(user.getBand()));
    }

    public UserResponseDTO createUserVenue(UserDTO userDTO, VenueDTO venueDTO) {
        User user = convertToEntity(userDTO);
        Venue venue = venueService.createVenue(venueConverter.convertToEntity(venueDTO));
        user.setVenue(venue);
        createUser(user);
        return convertToResponseDTOWithVenue(user, venueConverter.convertToDTO(user.getVenue()));
    }
}
