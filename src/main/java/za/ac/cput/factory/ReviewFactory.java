package za.ac.cput.factory;

import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ReviewFactory.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 26-Jul-24
 */

public class ReviewFactory {
    public static Review buildReview(Long id,
                                     Product product,
                                     User user,
                                     int rating,
                                     String comment
    ) {
        if (Helper.isNullOrEmpty(rating) ||
                Helper.isNullOrEmpty(comment)
        ) return null;

        return new Review.Builder()
                .setId(id)
                .setProduct(product)
                .setUser(user)
                .setRating(rating)
                .setComment(comment)
                .build();
    }
}
