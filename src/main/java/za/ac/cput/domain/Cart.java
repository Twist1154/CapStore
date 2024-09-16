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
 * Cart.java
 *
 * Author: Rethabile Ntsekhe
 * Date: 07-Sep-24
 */

@Getter
@Entity
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartid")
    private Long id;

    private Long userID;  // Restored userID

    @Column(name = "total_amount") // Explicitly map the column
    private double totalPrice;

    @CreationTimestamp
    private LocalDate cartDate;

    // OneToMany relationship with CartItems
    @Setter
    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItem> cartItems ;

    public Cart() {cartItems = new ArrayList<>();}

    public Cart(Builder builder) {
        this.id = builder.id;
        this.userID = builder.userID;
        this.totalPrice = builder.totalPrice;
        this.cartDate = builder.cartDate;
        this.cartItems = builder.cartItems;
    }

    // Method to add a cart item
    public void addCartItem(CartItem cartItem) {
        if (cartItem != null) {
            this.cartItems.add(cartItem);
            //this.totalPrice += cartItem.getPrice() * cartItem.getQuantity();
        }
    }
//
    // Method to remove a cart item
    public void removeCartItem(CartItem cartItem) {
        if (cartItem != null ) {
             this.cartItems.remove(cartItem);
            //this.totalPrice -= cartItem.getPrice() * cartItem.getQuantity();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Double.compare(cart.totalPrice, totalPrice) == 0 &&
                Objects.equals(id, cart.id) &&
                Objects.equals(userID, cart.userID) &&
                Objects.equals(cartDate, cart.cartDate) &&
                Objects.equals(cartItems, cart.cartItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userID, totalPrice, cartDate, cartItems);
    }

    @Override
    public String toString() {
        return "\n Cart: " +
                "\n id=" + id +
                "\n userID=" + userID +
                "\n totalPrice=" + totalPrice +
                "\n cartDate=" + cartDate +
                '}';
    }

    public static class Builder {
        private Long id;
        private Long userID;
        private double totalPrice;
        private LocalDate cartDate;
        private List<CartItem> cartItems = new ArrayList<>();

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUserID(Long userID) {
            this.userID = userID;
            return this;
        }

        public Builder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder setCartDate(LocalDate cartDate) {
            this.cartDate = cartDate;
            return this;
        }

        public Builder setCartItems(List<CartItem> cartItems) {
            this.cartItems = cartItems;
            return this;
        }

        public Builder copy(Cart cart) {
            this.id = cart.getId();
            this.userID = cart.getUserID();
            this.totalPrice = cart.getTotalPrice();
            this.cartItems = cart.getCartItems();
            this.cartDate = cart.getCartDate();
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}
