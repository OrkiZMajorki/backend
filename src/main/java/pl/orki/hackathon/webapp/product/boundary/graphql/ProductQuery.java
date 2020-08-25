package pl.orki.hackathon.webapp.product.boundary.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.product.entity.Product;
import pl.orki.hackathon.webapp.product.entity.ProductRepository;

import java.util.List;

@Component
public class ProductQuery implements GraphQLQueryResolver {

    private final ProductRepository productRepository;

    public ProductQuery(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> allProducts() {
        return productRepository.findAll();
    }
}
