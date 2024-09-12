package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * OrderItem.java
 * Entity representing an item in an order
 */


@Getter
@Entity
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productID;
    private int quantity;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Orders order;

    public OrderItem() {}

    public OrderItem(Builder builder) {
        this.id = builder.id;
        this.productID = builder.productID;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.order = builder.order;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem that)) return false;
        return quantity == that.quantity &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(productID, that.productID) &&
                Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productID, quantity, price, order);
    }

    @Override
    public String toString() {
        return "\n OrderItem{" +
                ", id=" + id +
                ", productID=" + productID +
                ", quantity=" + quantity +
                ", price=" + price +
                ", order=" + order.getId() +
                '}';
    }

    public static class Builder {
        private Long id;
        private Long productID;
        private int quantity;
        private double price;
        private Orders order;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setProductID(Long productID) {
            this.productID = productID;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setOrder(Orders order) {
            this.order = order;
            return this;
        }

        public Builder copy(OrderItem orderItem) {
            this.id = orderItem.id;
            this.productID = orderItem.productID;
            this.quantity = orderItem.quantity;
            this.price = orderItem.price;
            this.order = orderItem.order;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
