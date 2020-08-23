package pl.orki.hackathon.webapp.client.control.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Service;
import pl.orki.hackathon.webapp.client.entity.Client;
import pl.orki.hackathon.webapp.client.entity.ClientRepository;

import java.util.Optional;

@Service
public class ClientMutation implements GraphQLMutationResolver {

    private final ClientRepository clientRepository;

    public ClientMutation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // TODO: check if it's possible to pass as dto
    public Client createClient(String firstName, String lastName) {
        var client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);

        return clientRepository.save(client);
    }

    public Optional<Client> deleteClient(Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return client;
                });
    }
}
