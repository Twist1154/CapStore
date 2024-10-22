package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * SubCategory.java
 *
 * Author: Rethabile Ntsekhe
 * Student Num: 220455430
 * Date: 26-Sep-24
 */
@Getter
@Entity
public class SubCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "categories")
    @JsonBackReference(value = "product-category")
    private List<Product> products;

    public SubCategory() {
    }

    public SubCategory(Builder builder) {
        this.id = builder.id;
        this.category = builder.category;
        this.products = builder.products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCategory that = (SubCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(category, that.category) &&
                Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, products);
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "ID=" + id +
                ", Category=" + (category != null ? category.getName() : "null") +
                ", Products=" + (products != null ? products.size() : 0) +
                '}';
    }

    public static class Builder {
        private Long id;
        private Category category;
        private List<Product> products;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder setProduct(List<Product> products) {
            this.products = products;
            return this;
        }

        public Builder copy(SubCategory subCategory) {
            this.id = subCategory.id;
            this.category = subCategory.category;
            this.products = subCategory.products;
            return this;
        }

        public SubCategory build() {
            return new SubCategory(this);
        }
    }
}
