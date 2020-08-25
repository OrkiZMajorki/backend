package pl.orki.hackathon.webapp.client.boundary.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.client.control.ClientSpecifications;
import pl.orki.hackathon.webapp.client.entity.Client;
import pl.orki.hackathon.webapp.client.entity.ClientRepository;

import java.util.List;

@Component
public class ClientQuery implements GraphQLQueryResolver {

    private final ClientSpecifications clientSpecifications;
    private final ClientRepository clientRepository;

    public ClientQuery(ClientSpecifications clientSpecifications, ClientRepository clientRepository) {
        this.clientSpecifications = clientSpecifications;
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public List<Client> allClients(DataFetchingEnvironment environment) {
        DataFetchingFieldSelectionSet selectionSet = environment.getSelectionSet();

        if (selectionSet.contains("products")) {
            return clientRepository.findAll(clientSpecifications.fetchProducts());
        }

        return clientRepository.findAll();
    }
}
