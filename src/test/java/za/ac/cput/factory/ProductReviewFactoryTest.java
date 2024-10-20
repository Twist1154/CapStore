/**
 * E-commerce Web Application for selling T-shirts
 * ProductReviewFactoryTest.java
 * This class tests the ProductReviewFactory class
 * Author: Mthandeni Mbobo (218223579)
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductReview;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductReviewFactoryTest {

    Long productReviewID = 1L;
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
    void buildProductReview() {
        ProductReview productReview = ProductReviewFactory.buildProductReview(productReviewID, product, user, review, 5);
        assertNotNull(productReview);
        System.out.println(productReview);
    }

    @Test
    @Order(2)
// This test will print null, as product is null
    void buildProductReviewWithNullProductName() {
        ProductReview productReview = ProductReviewFactory.buildProductReview(productReviewID, null, user, review, 5);
        assertNull(productReview);
        System.out.println(productReview);
    }

    @Test
    @Order(3)
// This test will print null, as rating is out of range
    void buildProductReviewWithInvalidRating() {
        ProductReview productReview = ProductReviewFactory.buildProductReview(productReviewID, product, user, review, 6);
        assertNull(productReview);
        System.out.println(productReview);
    }

}