package pl.orki.hackathon.webapp.client.control.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.client.boundary.ClientDTO;
import pl.orki.hackathon.webapp.client.control.ClientConverter;
import pl.orki.hackathon.webapp.client.entity.Client;
import pl.orki.hackathon.webapp.client.entity.ClientRepository;

import java.util.Optional;

@Service
public class ClientMutation implements GraphQLMutationResolver {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    public ClientMutation(ClientRepository clientRepository, ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }

    @Transactional
    public Client createClient(ClientDTO clientDTO) {
        var client = clientConverter.toEntity(clientDTO);

        return clientRepository.save(client);
    }

    @Transactional
    public Optional<Client> deleteClient(Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return client;
                });
    }
}
