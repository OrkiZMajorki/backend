package pl.orki.hackathon.webapp.product.boundary;

import java.util.Objects;
import java.util.StringJoiner;

public class ProductClientDTO {

    private long productId;
    private long clientId;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductClientDTO that = (ProductClientDTO) o;
        return productId == that.productId &&
                clientId == that.clientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, clientId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProductClientDTO.class.getSimpleName() + "[", "]")
                .add("productId=" + productId)
                .add("clientId=" + clientId)
                .toString();
    }
}
