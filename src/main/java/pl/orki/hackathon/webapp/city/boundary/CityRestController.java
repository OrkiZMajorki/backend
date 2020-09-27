package pl.orki.hackathon.webapp.city.boundary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.orki.hackathon.webapp.city.boundary.dto.CityConverter;
import pl.orki.hackathon.webapp.city.boundary.dto.CityResponseDTO;
import pl.orki.hackathon.webapp.city.control.CityService;
import pl.orki.hackathon.webapp.city.entity.City;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/city")
public class CityRestController {

    private final CityService cityService;
    private final CityConverter cityConverter;

    public CityRestController(CityService cityService, CityConverter cityDTOConverter) {
        this.cityService = cityService;
        this.cityConverter = cityDTOConverter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponseDTO> getCityById(@PathVariable Long id) {
        Optional<CityResponseDTO> cityDTO = cityService.getCityById(id)
                .map(cityConverter::convertToResponseDTO);

        return ResponseEntity.of(cityDTO);
    }

    @GetMapping
    public ResponseEntity<List<CityResponseDTO>> getCities() {
        List<City> cities = cityService.getCities();
        List<CityResponseDTO> citiesDTO = cities.stream()
                .map(cityConverter::convertToResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(citiesDTO);
    }
}
