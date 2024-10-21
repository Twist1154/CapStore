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
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class ReviewFactory {

    public static Review buildReview(Long id, Product product, User user, String review, int rating){
        if (Helper.isNullOrEmpty(review) || !Helper.isValidRange(rating))
            return null;

        if (product == null || user == null)
            return null;

        return new Review.Builder()
                .setId(id)
                .setProduct(product)
                .setUser(user)
                .setReview(review)
                .setRating(rating)
                .build();
    }
}
