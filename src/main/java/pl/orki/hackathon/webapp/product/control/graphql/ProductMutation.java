package pl.orki.hackathon.webapp.product.control.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.client.entity.Client;
import pl.orki.hackathon.webapp.client.entity.ClientRepository;
import pl.orki.hackathon.webapp.product.boundary.ProductClientDTO;
import pl.orki.hackathon.webapp.product.boundary.ProductDTO;
import pl.orki.hackathon.webapp.product.entity.Product;
import pl.orki.hackathon.webapp.product.entity.ProductRepository;

import java.util.Optional;

@Service
public class ProductMutation implements GraphQLMutationResolver {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public ProductMutation(ClientRepository clientRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Product createProduct(ProductDTO productDTO) {
        var product = new Product();
        product.setName(productDTO.getName());

        return productRepository.save(product);
    }

    @Transactional
    public Optional<Product> addProductToClient(ProductClientDTO productClientDTO) {
        // TODO: add service in client for it?
        return clientRepository.findById(productClientDTO.getClientId())
                .flatMap(client -> productRepository.findById(productClientDTO.getProductId())
                        .map(product -> addProductToClient(client, product))
                );
    }

    private Product addProductToClient(Client client, Product product) {
        client.addProduct(product);
        clientRepository.save(client);

        return product;
    }
}
