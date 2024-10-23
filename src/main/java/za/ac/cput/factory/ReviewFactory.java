/**
 * E-commerce Web Application for selling T-shirts
 * ReviewFactory.java
 *
 * This class uses the Factory Pattern to create an instance of the Review entity
 * Author: Mthandeni Mbobo (218223579)
 * */

package za.ac.cput.factory;

import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.Users;
import za.ac.cput.util.Helper;

public class ReviewFactory {

    public static Review buildReview(Long id,
                                     Product product,
                                     Users users,
                                     String review,
                                     int rating){
        if (Helper.isNullOrEmpty(review) || !Helper.isValidRange(rating))
            return null;

        if (product == null || users == null)
            return null;

        return new Review.Builder()
                .setId(id)
                .setProduct(product)
                .setUsers(users)
                .setReview(review)
                .setRating(rating)
                .build();
    }
}
