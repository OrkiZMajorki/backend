package pl.orki.hackathon.webapp.client.control;

import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.client.boundary.ClientDTO;
import pl.orki.hackathon.webapp.client.entity.Client;

@Component
public class ClientConverter {

    public Client toEntity(ClientDTO clientDTO) {
        var client = new Client();
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());

        return client;
    }
}
