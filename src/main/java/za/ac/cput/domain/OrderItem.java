package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * OrderItem.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 07-Sep-24
 */

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private Long productID;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "orderID", nullable = false) // Define the many-to-one relationship
    private Orders orders; // Link to Orders

    protected OrderItem() {}

    public OrderItem(Builder builder) {
        this.orderItemId = builder.orderItemId;
        this.productID = builder.productID;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.orders = builder.orders; // Set orders in constructor
    }

    public Long getOrderItemId() {
        return orderItemId;
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

    public Orders getOrders() { // Getter for orders
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity &&
                Double.compare(orderItem.price, price) == 0 &&
                Objects.equals(orderItemId, orderItem.orderItemId) &&
                Objects.equals(productID, orderItem.productID) &&
                Objects.equals(orders, orderItem.orders); // Include orders in equality check
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemId, productID, quantity, price, orders); // Include orders in hash code
    }

    @Override
    public String toString() {
        return "\n OrderItem{" +
                "orderItemId=" + orderItemId +'\n'+
                ", productID=" + productID +'\n'+
                ", quantity=" + quantity +'\n'+
                ", price=" + price +'\n'+
                ", orders=" + orders +'\n'+ // Include orders in toString
                '}'+'\n';
    }

    public static class Builder {
        private Long orderItemId;
        private Long productID;
        private int quantity;
        private double price;
        private Orders orders; // Builder field for orders

        public Builder setOrderItemId(Long orderItemId) {
            this.orderItemId = orderItemId;
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

        public Builder setOrders(Orders orders) { // Builder method for orders
            this.orders = orders;
            return this;
        }

        public Builder copy(OrderItem orderItem) {
            this.orderItemId = orderItem.getOrderItemId();
            this.productID = orderItem.getProductID();
            this.quantity = orderItem.getQuantity();
            this.price = orderItem.getPrice();
            this.orders = orderItem.getOrders(); // Copy orders
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}

