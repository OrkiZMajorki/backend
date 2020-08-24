package pl.orki.hackathon.webapp.product.entity;

import pl.orki.hackathon.webapp.client.entity.Client;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

// TODO: separate class for response
// TODO: explicit columns, separate id generator
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // TODO: check for alternative of eagerly loading
    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private Set<Client> clients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
