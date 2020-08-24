package pl.orki.hackathon.webapp.product.boundary;

import java.util.Objects;
import java.util.StringJoiner;

public class ProductDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProductDTO.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
