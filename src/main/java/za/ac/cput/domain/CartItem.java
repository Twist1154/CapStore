package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a cart item entry in the system.
 * Each entry is associated with a Cart, a Product, and a ProductSkuService.
 * <p>
 * This entity class is mapped to the "cart_item" table in the database.
 * Includes the necessary mappings for relationships to other entities.
 *
 * @author Rethabile Ntsekhe
 * @date 25-Aug-24
 */
@Entity
@Getter
@Table(name = "cart_item")
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonBackReference("cartReference")
    @JsonIncludeProperties("id")
    private Cart cart;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIncludeProperties("id")
    private Product product;


    @Column(nullable = false)
    private int quantity;

    public CartItem() {
    }

    private CartItem(Builder builder) {
        this.id = builder.id;
        this.cart = builder.cart;
        this.product = builder.product;
        this.quantity = builder.quantity;
    }

    @Override
    public String toString() {
        return "\n CartItem{" +
                "id=" + id +
                ", cart=" + cart +
                ", product=" + product.getName() + '\'' +
                ", quantity=" + quantity +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return quantity == cartItem.quantity &&
                Objects.equals(id, cartItem.id) &&
                Objects.equals(cart, cartItem.cart) &&
                Objects.equals(product, cartItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cart, product, quantity);
    }

    public static class Builder {
        private Long id;
        private Cart cart;
        private Product product;
        private int quantity;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }


        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder copy(CartItem cartItem) {
            this.id = cartItem.getId();
            this.cart = cartItem.getCart();
            this.product = cartItem.getProduct();
            this.quantity = cartItem.getQuantity();
            return this;
        }

        public CartItem build() {
            return new CartItem(this);
        }
    }
}
