package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Orders.java
 *
 * @author Rethabile Ntsekhe
 * @date 07-Sep-24
 */

@Getter
@Entity
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userID;
    private Long addressID;
    private double totalPrice;
    private String status;

    @CreationTimestamp
    private LocalDate orderDate;

    // OneToMany relationship with OrderItems
    @Setter
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Orders() {
    }

    public Orders(Builder builder) {
        this.id = builder.id;
        this.userID = builder.userID;
        this.addressID = builder.addressID;
        this.totalPrice = builder.totalPrice;
        this.status = builder.status;
        this.orderDate = builder.orderDate;
        this.orderItems = builder.orderItems;
    }

    // Method to add an order item
    public void addOrderItem(OrderItem orderItem) {
        if (orderItem != null) {
            this.orderItems.add(orderItem);

            // Update total price
            this.totalPrice += orderItem.getPrice() * orderItem.getQuantity();
        }
    }

    // Method to remove an order item
    public void removeOrderItem(OrderItem orderItem) {
        if (orderItem != null && this.orderItems.remove(orderItem)) {
            // Update total price
            this.totalPrice -= orderItem.getPrice() * orderItem.getQuantity();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Double.compare(orders.totalPrice, totalPrice) == 0 &&
                Objects.equals(id, orders.id) &&
                Objects.equals(userID, orders.userID) &&
                Objects.equals(addressID, orders.addressID) &&
                Objects.equals(status, orders.status) &&
                Objects.equals(orderDate, orders.orderDate) &&
                Objects.equals(orderItems, orders.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                userID,
                addressID,
                totalPrice,
                status,
                orderDate,
                orderItems
        );
    }

    @Override
    public String toString() {
        return "\n Orders: " +
                "\n id=" + id +
                "\n userID=" + userID +
                "\n addressID=" + addressID +
                "\n totalPrice=" + totalPrice +
                "\n status='" + status + '\'' +
                "\n orderDate=" + orderDate +
                "\n orderItems size: " + orderItems.size() +
                '}';
    }

    public static class Builder {
        private Long id;
        private Long userID;
        private Long addressID;
        private String status;
        private double totalPrice;
        private LocalDate orderDate;
        private List<OrderItem> orderItems = new ArrayList<>();

        public Builder setId(Long id) {
            this.id = id;
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

        public Builder setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Builder copy(Orders orders) {
            this.id = orders.getId();
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
