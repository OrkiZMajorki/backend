package pl.orki.hackathon.webapp.client.control;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.orki.hackathon.webapp.client.entity.Client;

import javax.persistence.criteria.JoinType;

@Component
public class ClientSpecifications {

    public Specification<Client> fetchProducts() {
        return (root, query, builder) -> {
            root.fetch("products", JoinType.LEFT);
            return root.join("products", JoinType.LEFT).getOn();
        };
    }
}
