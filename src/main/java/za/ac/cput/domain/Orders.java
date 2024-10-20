package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
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
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    @JsonManagedReference("addressReference")
    private Address address;

    private double totalPrice;

    private String status;

    @CreationTimestamp
    private LocalDate orderDate;


    @OneToMany
    @JoinColumn(name = "order_id")
    @JsonManagedReference("orderItemReference")
    @JsonIgnore
    private List<OrderItem> orderItems;

    public Orders() {
    }

    public Orders(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.address = builder.address;
        this.totalPrice = builder.totalPrice;
        this.status = builder.status;
        this.orderDate = builder.orderDate;
        this.orderItems = builder.orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Double.compare(orders.totalPrice, totalPrice) == 0 &&
                Objects.equals(id, orders.id) &&
                Objects.equals(user, orders.user) &&
                Objects.equals(address, orders.address) &&
                Objects.equals(status, orders.status) &&
                Objects.equals(orderDate, orders.orderDate) &&
                Objects.equals(orderItems, orders.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                user,
                address,
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
                "\n userID=" + user +
                "\n addressID=" + address +
                "\n totalPrice=" + totalPrice +
                "\n status='" + status + '\'' +
                "\n orderDate=" + orderDate +
                "\n orderItems size: " + orderItems.size() +
                '}';
    }

    public static class Builder {
        private Long id;
        private User user;
        private Address address;
        private String status;
        private double totalPrice;
        private LocalDate orderDate;
        private List<OrderItem> orderItems;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
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
            this.user = orders.getUser();
            this.address = orders.getAddress();
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
