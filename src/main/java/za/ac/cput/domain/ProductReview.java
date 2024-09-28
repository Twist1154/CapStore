/**
 * E-commerce Web Application for selling T-shirts
 * ProductReview.java
 * POJO class for the ProductReview entity, using the Builder Pattern
 * Author: Mthandeni Mbobo (218223579)
 * */

package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity(name = "product_review")
public class ProductReview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productReviewId;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    private String review;
    private int rating;

    public ProductReview() {
    }

    public ProductReview(Builder builder) {
        this.productReviewId = builder.productReviewId;
        this.product = builder.product;
        this.user = builder.user;
        this.review = builder.review;
        this.rating = builder.rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductReview that = (ProductReview) o;
        return rating == that.rating && Objects.equals(productReviewId, that.productReviewId) && Objects.equals(product, that.product) && Objects.equals(user, that.user) && Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productReviewId, product, user, review, rating);
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "productReviewId=" + productReviewId +
                ", product=" + product +
                ", user=" + user +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }

    public  static class  Builder{
        private Long productReviewId;
        private Product product;
        private User user;
        private String review;
        private int rating;

        public Builder setProductReviewId(Long productReviewId) {
            this.productReviewId = productReviewId;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
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
            this.product = productReview.product;
            this.user = productReview.user;
            this.review = productReview.review;
            this.rating = productReview.rating;
            return this;
        }

        public ProductReview build(){
            return new ProductReview(this);
        }
    }

}
