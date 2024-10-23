package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference("orderReference")
    private Orders order;

    public OrderItem() {}

    public OrderItem(Builder builder) {
        this.id = builder.id;
        this.product = builder.product;
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
                Objects.equals(product, that.product) &&
                Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, quantity, price, order);
    }

    @Override
    public String toString() {
        return "\n OrderItem{" +
                ", id=" + id +
                ", productID=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", order=" + order.getId() +
                '}';
    }

    public static class Builder {
        private Long id;
        private Product product;
        private int quantity;
        private double price;
        private Orders order;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
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
            this.product = orderItem.product;
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
