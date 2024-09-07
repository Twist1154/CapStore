package za.ac.cput.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * OrderItem.java
 * Embedded class representing items in an order
 */
@Embeddable
public class OrderItem implements Serializable {
    private Long productID;
    private int quantity;
    private double price;

    public OrderItem() {}

    public OrderItem(Long productID, int quantity, double price) {
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity && Double.compare(orderItem.price, price) == 0 && Objects.equals(productID, orderItem.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, quantity, price);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "productID=" + productID +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
