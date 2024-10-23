package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Product.java
 *
 * Author: Zachariah Matsimella
 * Student Num: 220097429
 * Date: 06-Sep-24
 */
@Getter
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private int stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference("productReference")
    @JsonIgnore
    private List<SubCategory> categories ;

    @ManyToMany
    @JoinTable(
            name = "product_discount", // Inverse table for discounts
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_id")
    )
    @JsonIgnore
    private List<Discount> discounts = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Embedded
    private Images images;

    public Product() {}

    public Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.stock = builder.stock;
        this.categories = builder.categories != null ? builder.categories : new ArrayList<>();
        this.discounts = builder.discounts != null ? builder.discounts : new ArrayList<>();
        this.images = builder.images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                stock == product.stock &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(categories, product.categories) &&
                Objects.equals(discounts, product.discounts) &&
                Objects.equals(createdAt, product.createdAt) &&
                Objects.equals(updatedAt, product.updatedAt) &&
                Objects.equals(images, product.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, stock, categories, discounts, createdAt, updatedAt, images);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", CATEGORIES: " + (categories != null ? categories : "[]") +
                ", DISCOUNTS: " + (discounts != null ? discounts : "[]") +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", images=" + images +
                '}';
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private double price;
        private int stock;
        private List<SubCategory> categories;
        private List<Discount> discounts;
        private Images images;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setStock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder setCategories(List<SubCategory> categories) {
            this.categories = categories;
            return this;
        }

        public Builder setDiscounts(List<Discount> discounts) {
            this.discounts = discounts;
            return this;
        }

        public Builder setImages(Images images) {
            this.images = images;
            return this;
        }

        public Builder copy(Product product) {
            this.id = product.getId();
            this.name = product.getName();
            this.description = product.getDescription();
            this.price = product.getPrice();
            this.stock = product.getStock();
            this.categories = product.getCategories();
            this.discounts = product.getDiscounts();
            this.images = product.getImages();
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
