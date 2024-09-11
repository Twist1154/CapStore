package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartID;

    private Long customerID;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartItem> cartItems = new ArrayList<>();

    private double totalAmount;


    protected Cart() {}


    private Cart(Builder builder) {
        this.cartID = builder.cartID;
        this.customerID = builder.customerID;
        this.cartItems = builder.cartItems;
        this.totalAmount = builder.totalAmount;
    }


    public Long getCartID() {
        return cartID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return cartID == cart.cartID && customerID == cart.customerID &&
                Double.compare(cart.totalAmount, totalAmount) == 0 &&
                Objects.equals(cartItems, cart.cartItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartID, customerID, cartItems, totalAmount);
    }


    @Override
    public String toString() {
        return "Cart{" +
                "cartID=" + cartID +
                ", customerID=" + customerID +
                ", cartItems=" + cartItems +
                ", totalAmount=" + totalAmount +
                '}';
    }


    public static class Builder {
        private Long cartID;
        private Long customerID;
        private List<CartItem> cartItems = new ArrayList<>(); // Initialize list to avoid null pointer issues
        private double totalAmount;

        public Builder setCartID(Long cartID) {
            this.cartID = cartID;
            return this;
        }

        public Builder setCustomerID(Long customerID) {
            this.customerID = customerID;
            return this;
        }

        public Builder setCartItems(List<CartItem> cartItems) {
            this.cartItems = cartItems;
            return this;
        }

        public Builder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }


        public Builder copy(Cart cart) {
            this.cartID = cart.cartID;
            this.customerID = cart.customerID;
            this.cartItems = cart.cartItems;
            this.totalAmount = cart.totalAmount;
            return this;
        }


        public Cart build() {
            return new Cart(this);
        }
    }
}
