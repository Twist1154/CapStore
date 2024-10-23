package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Represents a cart entry in the system.
 * Each entry is associated with a User.
 *
 * This entity class is mapped to the "cart" table in the database.
 * Includes necessary mappings for relationships to other entities.
 *
 * @author Rethabile Ntsekhe
 * @date 25-Aug-24
 */
@Entity
@Getter
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIncludeProperties("id")
    private Users users;

    private Double total;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("cartReference")
    private List<CartItem> cartItems;

    @OneToOne
    @JoinColumn(name = "discount_id", nullable = true)
    private Discount discount;

    public Cart() {}

    private Cart(Builder builder) {
        this.id = builder.id;
        this.users = builder.users;
        this.total = builder.total;
        this.discount = builder.discount;
    }

    @Override
    public String toString() {
        return "\n Cart{" +
                "id=" + id +
                ", user=" + users.getFirstName() +
                ", totalPrice=" + total +
                ", discount=" + discount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) &&
                Objects.equals(users, cart.users) &&
                Objects.equals(total, cart.total) &&
                Objects.equals(createdAt, cart.createdAt) &&
                Objects.equals(updatedAt, cart.updatedAt) &&
                Objects.equals(cartItems, cart.cartItems) &&
                Objects.equals(discount, cart.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, users, total, createdAt, updatedAt, cartItems, discount);
    }

    public static class Builder {
        private Long id;
        private Users users;
        private Double total;
        private Discount discount;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUsers(Users users) {
            this.users = users;
            return this;
        }

        public Builder setTotal(Double total) {
            this.total = total;
            return this;
        }

        public Builder setDiscount(Discount discount) {
            this.discount = discount;
            return this;
        }

        public Builder copy(Cart cart) {
            this.id = cart.getId();
            this.users = cart.getUsers();
            this.total = cart.getTotal();
            this.discount = cart.getDiscount();
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}
