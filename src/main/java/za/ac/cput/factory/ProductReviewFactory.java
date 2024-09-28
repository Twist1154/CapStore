/**
 * E-commerce Web Application for selling T-shirts
 * ProductReviewFactory.java
 * This class uses the Factory Pattern to create an instance of the ProductReview entity
 * Author: Mthandeni Mbobo (218223579)
 * */

package za.ac.cput.factory;

import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductReview;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class ProductReviewFactory {

    public static ProductReview buildProductReview(Long productReviewId, Product product, User user, String review, int rating){
        if (Helper.isNullOrEmpty(review) || !Helper.isValidRange(rating))
            return null;

        if (product == null || user == null)
            return null;

        return new ProductReview.Builder()
                .setProductReviewId(productReviewId)
                .setProduct(product)
                .setUser(user)
                .setReview(review)
                .setRating(rating)
                .build();
    }

}
