package za.ac.cput.domain;
/**
 * E-Commerce Web Application for selling clothes
 * CartItem.java
 * This POJO class for the CartItem entity. Domain class using Builder Pattern
 * Author: Kinzonzi Genereux Mukoko - 221477934
 * Date: 14 May 2024
 */
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class CartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemID;
    private Long cartID;
    private Long productID;
    private double price;

    // Default constructor is required by JPA
    protected CartItem() {}

    // Constructor for Builder pattern
    private CartItem(Builder builder) {
        this.cartItemID = builder.cartItemID;
        this.cartID = builder.cartID;
        this.productID = builder.productID;
        this.price = builder.price;
    }

    public Long getCartItemID() {
        return cartItemID;
    }

    public Long getCartID() {
        return cartID;
    }

    public Long getProductID() {
        return productID;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return cartItemID == cartItem.cartItemID &&
                cartID == cartItem.cartID &&
                productID == cartItem.productID &&
                Double.compare(cartItem.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItemID, cartID, productID, price);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemID=" + cartItemID +
                ", cartID=" + cartID +
                ", productID=" + productID +
                ", price=" + price +
                '}';
    }

    // Builder class
    public static class Builder {
        private Long cartItemID;
        private Long cartID;
        private Long productID;
        private double price;

        public Builder setCartItemID(Long cartItemID) {
            this.cartItemID = cartItemID;
            return this;
        }

        public Builder setCartID(Long cartID) {
            this.cartID = cartID;
            return this;
        }

        public Builder setProductID(Long productID) {
            this.productID = productID;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder copy(CartItem cartItem) {
            this.cartItemID = cartItem.cartItemID;
            this.cartID = cartItem.cartID;
            this.productID = cartItem.productID;
            this.price = cartItem.price;
            return this;
        }

        public CartItem build() {
            return new CartItem(this);
        }
    }
}
