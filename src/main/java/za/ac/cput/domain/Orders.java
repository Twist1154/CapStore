package za.ac.cput.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Orders.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 07-Sep-24
 */

@Entity
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;
    private Long userID;
    private Long addressID;
    private double totalPrice;
    private String status;

    private LocalDateTime orderDate;

    // ElementCollection for OrderItems
    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> orderItems = new ArrayList<>();  // Initialize the list

    public Orders() {}

    public Orders(Builder builder) {
        this.orderID = builder.orderID;
        this.userID = builder.userID;
        this.addressID = builder.addressID;
        this.totalPrice = builder.totalPrice;
        this.status = builder.status;
        this.orderDate = builder.orderDate;
        this.orderItems = builder.orderItems;
    }

    public Long getOrderID() {
        return orderID;
    }

    public Long getUserID() {
        return userID;
    }

    public Long getAddressID() {
        return addressID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders orders)) return false;
        return Double.compare(totalPrice, orders.totalPrice) == 0 && Objects.equals(orderID, orders.orderID) && Objects.equals(userID, orders.userID) && Objects.equals(addressID, orders.addressID) && Objects.equals(status, orders.status) && Objects.equals(orderDate, orders.orderDate) && Objects.equals(orderItems, orders.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, userID, addressID, totalPrice, status, orderDate, orderItems);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderID=" + orderID + '\n' +
                ", userID='" + userID + '\n' +
                ", addressID='" + addressID + '\n' +
                ", status='" + status + '\n' +
                ", orderDate=" + orderDate + '\n' +
                ", orderItems=" + orderItems + '\n' +
                '}' + '\n';
    }

    public static class Builder {
        private Long orderID;
        private Long userID;
        private Long addressID;
        private String status;
        private double totalPrice;
        private LocalDateTime orderDate;
        private List<OrderItem> orderItems;

        public Builder setOrderID(Long orderID) {
            this.orderID = orderID;
            return this;
        }

        public Builder setUserID(Long userID) {
            this.userID = userID;
            return this;
        }

        public Builder setAddressID(Long addressID) {
            this.addressID = addressID;
            return this;
        }

        public Builder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setOrderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Builder copy(Orders orders) {
            this.orderID = orders.getOrderID();
            this.userID = orders.getUserID();
            this.addressID = orders.getAddressID();
            this.totalPrice = orders.getTotalPrice();
            this.status = orders.getStatus();
            this.orderItems = orders.getOrderItems();
            this.orderDate = orders.getOrderDate();
            return this;
        }

        public Orders build() {
            return new Orders(this);
        }
    }
}
