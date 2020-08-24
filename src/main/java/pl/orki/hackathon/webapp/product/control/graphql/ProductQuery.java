package pl.orki.hackathon.webapp.product.control.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.orki.hackathon.webapp.product.entity.Product;
import pl.orki.hackathon.webapp.product.entity.ProductRepository;

import java.util.List;

@Service
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
