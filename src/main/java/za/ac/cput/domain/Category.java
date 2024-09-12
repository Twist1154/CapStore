/**
 * E-commerce Web Application for selling T-shirts
 * Category.java
 * POJO class for the Category entity, using the Builder Pattern
 * Author: Mthandeni Mbobo (218223579)
 * */

package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    private String subCategoryName;

    public Category() {
    }

    public Category (Builder builder){
        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;;
        this.subCategoryName = builder.subCategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryId, category.categoryId) && Objects.equals(categoryName, category.categoryName) && Objects.equals(subCategoryName, category.subCategoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, subCategoryName);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                '}';
    }

    public static class Builder{
        private Long categoryId;
        private String categoryName;
        private String subCategoryName;

        public Builder setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder setSubCategoryName(String subCategoryName) {
            this.subCategoryName = subCategoryName;
            return this;
        }

        public Builder copy(Category category){
            this.categoryId = category.categoryId;
            this.categoryName = category.categoryName;
            this.subCategoryName = category.subCategoryName;
            return this;
        }

        public Category build(){
            return new Category(this);
        }
    }

}
