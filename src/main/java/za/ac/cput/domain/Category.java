/**
 * E-commerce Web Application for selling T-shirts
 * Category.java
 * POJO class for the Category entity, using the Builder Pattern
 * Author: Mthandeni Mbobo (218223579)
 */

package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Entity(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // Corrected field name to lowercase 'name'
    @OneToMany
    @JoinColumn(name = "category_id")

    private List<SubCategory> subCategories;


    public Category() {
    }

    public Category(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.subCategories = builder.subCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(subCategories, category.subCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, subCategories);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + id +
                ", categoryName='" + name + '\'' +
                ", subCategoryName='" + subCategories + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private String name;
        private List<SubCategory> subCategories;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSubCategories(List<SubCategory> subCategories) {
            this.subCategories = subCategories;
            return this;
        }

        public Builder copy(Category category) {
            this.id = category.id;
            this.name = category.name;
            this.subCategories = category.subCategories;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
