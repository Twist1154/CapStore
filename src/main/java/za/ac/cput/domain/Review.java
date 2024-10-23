/**
 * E-commerce Web Application for selling T-shirts
 * Review.java
 *
 * POJO class for the Review entity, using the Builder Pattern
 * Author: Mthandeni Mbobo (218223579)
 * */

package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
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
    private Users users;

    private String review;
    private int rating;

    public Review() {
    }

    public Review(Builder builder) {
        this.id = builder.id;
        this.product = builder.product;
        this.users = builder.users;
        this.review = builder.review;
        this.rating = builder.rating;
    }

    public Review(Long id, Product product, Users users, String review, int rating) {
        this.id = id;
        this.product = product;
        this.users = users;
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
                Objects.equals(users, that.users) &&
                Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, users, review, rating);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", product=" + (product != null ? product.getId() : "null") +
                ", user=" + (users != null ? users.getId() : "null") +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }

    public static class Builder {
        private Long id;
        private Product product;
        private Users users;
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

        public Builder setUsers(Users users) {
            this.users = users;
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
            this.users = review.users;
            this.review = review.review;
            this.rating = review.rating;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}