/**
 * E-commerce Web Application for selling T-shirts
 * Review.java
 * POJO class for the Review entity, using the Builder Pattern
 * Author: Mthandeni Mbobo (218223579)
 * */

package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "review")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_Id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String review;
    private int rating;

    public Review() {
    }

    public Review(Builder builder) {
        this.id = builder.id;
        this.product = builder.product;
        this.user = builder.user;
        this.review = builder.review;
        this.rating = builder.rating;
    }

    public Review(Long id, Product product, User user, String review, int rating) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.review = review;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review that = (Review) o;
        return rating == that.rating &&
                Objects.equals(id, that.id) &&
                Objects.equals(product, that.product) &&
                Objects.equals(user, that.user) &&
                Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, user, review, rating);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", product=" + (product != null ? product.getProductId() : "null") +
                ", user=" + (user != null ? user.getUserID() : "null") +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }

    public static class Builder {
        private Long id;
        private Product product;
        private User user;
        private String review;
        private int rating;

        public Builder setId(Long id) {
            this.id = id;
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

        public Builder copy(Review review) {
            this.id = review.id;
            this.product = review.product;
            this.user = review.user;
            this.review = review.review;
            this.rating = review.rating;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}