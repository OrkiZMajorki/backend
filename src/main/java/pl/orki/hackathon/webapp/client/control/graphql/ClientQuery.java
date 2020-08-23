package pl.orki.hackathon.webapp.client.control.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.client.entity.Client;
import pl.orki.hackathon.webapp.client.entity.ClientRepository;

import java.util.List;

@Service
public class ClientQuery implements GraphQLQueryResolver {

    private final ClientRepository clientRepository;

    public ClientQuery(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public List<Client> allClients() {
        return clientRepository.findAll();
    }
}
