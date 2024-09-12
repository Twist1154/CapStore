/**
 * E-commerce Web Application for selling T-shirts
 * ProductReview.java
 * POJO class for the ProductReview entity, using the Builder Pattern
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
public class ProductReview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productReviewId;
    private  String productName;
    private String customerName;
    private String review;
    private int rating;

    public ProductReview() {
    }

    public ProductReview(Builder builder) {
        this.productReviewId = builder.productReviewId;
        this.productName = builder.productName;
        this.customerName = builder.customerName;
        this.review = builder.review;
        this.rating = builder.rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductReview that = (ProductReview) o;
        return rating == that.rating && Objects.equals(productReviewId, that.productReviewId) && Objects.equals(productName, that.productName) && Objects.equals(customerName, that.customerName) && Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productReviewId, productName, customerName, review, rating);
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "productReviewId=" + productReviewId +
                ", productName='" + productName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }

    public  static class  Builder{
        private Long productReviewId;
        private String productName;
        private String customerName;
        private String review;
        private int rating;

        public Builder setProductReviewId(Long productReviewId) {
            this.productReviewId = productReviewId;
            return this;
        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder setReview(String review) {
            this.review = review;
            return this;
        }

        public Builder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public  Builder copy(ProductReview productReview){
            this.productReviewId = productReview.productReviewId;
            this.productName = productReview.productName;
            this.customerName = productReview.customerName;
            this.review = productReview.review;
            this.rating = productReview.rating;
            return this;
        }

        public ProductReview build(){
            return new ProductReview(this);
        }
    }

}
