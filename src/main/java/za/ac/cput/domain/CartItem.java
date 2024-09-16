package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_itemid")
    private Long id;

    private Long productId;
    private double price;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // Default constructor
    public CartItem() {}

    // Private constructor for Builder
    private CartItem(Builder builder) {
        this.id = builder.id;
        this.productId = builder.productId;
        this.price = builder.price;
        this.cart = builder.cart;
    }


    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Double.compare(cartItem.price, price) == 0 &&
                Objects.equals(id, cartItem.id) &&
                Objects.equals(productId, cartItem.productId) &&
                Objects.equals(cart, cartItem.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, price, cart);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", productId=" + productId +
                ", price=" + price +
                ", cart=" + cart +
                '}';
    }

    public Long getProductID() {
        return productId;
    }


    // Builder class
    public static class Builder {
        private Long id;
        private Long productId;
        private double price;
        private Cart cart;

        // Default constructor for Builder
        public Builder() {}

        // Constructor to create a builder from an existing CartItem
        private Builder(CartItem cartItem) {
            this.id = cartItem.getId();
            this.productId = cartItem.getProductID();
            this.price = cartItem.getPrice();
            this.cart = cartItem.getCart();
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setProductID(Long productID) {
            this.productId = productID;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setCart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public CartItem build() {
            return new CartItem(this);
        }

        public CartItem.Builder copy(CartItem cartitem) {
            this.id = cartitem.getId();
            this.productId = cartitem.getProductID();
            this.price = cartitem.getPrice();
            this.cart = cartitem.getCart();
            return this;
        }
        // Static copy method
        public static Builder from(CartItem cartItem) {
            return new Builder(cartItem);
        }

        
    }
}
