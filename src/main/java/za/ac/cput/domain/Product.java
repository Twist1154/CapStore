package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Product.java
 *
 * @author Zachariah Matsimella
 * Student Num: 220097429
 * @date 06-Sep-24
 */
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Long categoryId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "imageId"))
    @Column(name = "imageUrl")
    private List<String> imageUrl= new ArrayList<>();

    public Product() {
    }

    public Product(Builder builder){
        this.productId = builder.productId;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.stock = builder.stock;
        this.categoryId = builder.categoryId;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.imageUrl = builder.imageUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && stock == product.stock && Objects.equals(productId, product.productId) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(categoryId, product.categoryId) && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt) && Objects.equals(imageUrl, product.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, description, price, stock, categoryId, createdAt, updatedAt, imageUrl);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", categoryId=" + categoryId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public static class Builder{
        private Long productId;
        private String name;
        private String description;
        private double price;
        private int stock;
        private Long categoryId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<String> imageUrl= new ArrayList<>();;

        public Builder setProductId(Long productId){
            this.productId = productId;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setDescription(String description){
            this.description = description;
            return this;
        }

        public Builder setPrice(double price){
            this.price = price;
            return this;
        }

        public Builder setStock(int stock){
            this.stock = stock;
            return this;
        }

        public Builder setCategoryId(Long categoryId){
            this.categoryId = categoryId;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt){
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt){
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder setImagePath(List<String> imageUrl){
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder copy(Product product) {
            this.productId = product.getProductId();
            this.name = product.getName();
            this.description = product.getDescription();
            this.price = product.getPrice();
            this.stock = product.getStock();
            this.categoryId = product.getCategoryId();
            this.createdAt = product.getCreatedAt();
            this.updatedAt = product.getUpdatedAt();
            this.imageUrl = product.getImageUrl();
            return this;
        }

        public Product build(){
            return new Product(this);
        }
    }
}
