/**
 * E-commerce Web Application for selling T-shirts
 * ReviewFactoryTest.java
 *
 * This class tests the ReviewFactory class
 * Author: Mthandeni Mbobo (218223579)
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewFactoryTest {

    Long id = 1L;
    String review = "Great product!";

    Product product = new Product.Builder()
            .setId(1L)
            .setName("Test Product")
            .build();

    User user = new User.Builder()
            .setId(1L)
            .setFirstName("Mthandeni")
            .setLastName("Mbobo")
            .build();

    @Test
    @Order(1)
 // This test will print productReview, all parameters are not null
    void buildReview() {
        Review review = ReviewFactory.buildReview(id, product, user, this.review, 5);
        assertNotNull(review);
        System.out.println(review);
    }

    @Test
    @Order(2)
// This test will print null, as product is null
    void buildProductReviewWithNullName() {
        Review review = ReviewFactory.buildReview(id, null, user, this.review, 5);
        assertNull(review);
        System.out.println(review);
    }

    @Test
    @Order(3)
// This test will print null, as rating is out of range
    void buildReviewWithInvalidRating() {
        Review review = ReviewFactory.buildReview(id, product, user, this.review, 6);
        assertNull(review);
        System.out.println(review);
    }

}