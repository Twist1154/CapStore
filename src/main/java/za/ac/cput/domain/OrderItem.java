package za.ac.cput.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * OrderItem.java
 * Entity representing an item in an order_id
 */

@Entity
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemID;

    private Long productID;
    private int quantity;
    private double price;

    private Long order_id;

    public OrderItem() {}

    public OrderItem(Builder builder) {
        this.orderItemID = builder.orderItemID;
        this.productID = builder.productID;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.order_id = builder.order_id;
    }

    public Long getOrderItemID() {
        return orderItemID;
    }

    public Long getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Long getOrder_id() {
        return order_id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem that = (OrderItem) o;
        return quantity == that.quantity &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(orderItemID, that.orderItemID) &&
                Objects.equals(productID, that.productID) &&
                Objects.equals(order_id, that.order_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemID, productID, quantity, price, order_id);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemID=" + orderItemID +
                ", productID=" + productID +
                ", quantity=" + quantity +
                ", price=" + price +
                ", order_id=" + order_id +
                '}';
    }

    public static class Builder {
        private Long orderItemID;
        private Long productID;
        private int quantity;
        private double price;
        private Long order_id;

        public Builder setOrderItemID(Long orderItemID) {
            this.orderItemID = orderItemID;
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

        public Builder setOrder_id(Long order_id) {
            this.order_id = order_id;
            return this;
        }

        public Builder copy(OrderItem orderItem) {
            this.orderItemID = orderItem.orderItemID;
            this.productID = orderItem.productID;
            this.quantity = orderItem.quantity;
            this.price = orderItem.price;
            this.order_id = orderItem.order_id;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
