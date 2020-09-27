package pl.orki.hackathon.webapp.city.boundary.dto;

import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.city.entity.City;

@Component
public class CityConverter {

    public CityResponseDTO convertToResponseDTO(City city) {
        var dto = new CityResponseDTO();
        dto.setId(city.getId());
        dto.setName(city.getName());

        return dto;
    }
}
