package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Reviews.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 23-Jul-24
 */

@Getter
@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonManagedReference("productReviewReference")
    @JsonIncludeProperties("id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference("userReviewReference")
    @JsonIncludeProperties({"id", "firstName", "lastName", "avatar"})
    private User user;

    private int rating;
    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Review() {
    }

    public Review(Builder builder) {
        this.id = builder.id;
        this.product = builder.product;
        this.user = builder.user;
        this.rating = builder.rating;
        this.comment = builder.comment;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && rating == review.rating && Objects.equals(product, review.product) && Objects.equals(user, review.user) && Objects.equals(comment, review.comment) && Objects.equals(createdAt, review.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, user, rating, comment, createdAt);
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "Review ID: " + id +
                ", PRODUCT : " + product.getName() +
                ", USER : " + user.getFirstName() +
                ", RATING: " + rating +
                ", COMMENT: '" + comment + '\'' +
                ", CREATED AT: " + createdAt +
                '}';
    }

    public static class Builder {
        private Long id;
        private Product product;
        private User user;
        private int rating;
        private String comment;

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

        public Builder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder copy(Review reviews) {
            this.id = reviews.getId();
            this.product = reviews.getProduct();
            this.user = reviews.getUser();
            this.rating = reviews.getRating();
            this.comment = reviews.getComment();
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}

