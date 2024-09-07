/**
 * E-commerce Web Application for selling T-shirts
 * ProductReviewFactoryTest.java
 * This class tests the ProductReviewFactory class
 * Author: Mthandeni Mbobo (218223579)
 * */

package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.ProductReview;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductReviewFactoryTest {

    Long productReviewID = 1L;
    String productName = "White Lousy T-shirt";
    String customerName = "Siya Mthandeni";
    String review = "Great product!";

    @Test
    @Order(1)
    //This test will pass, all parameters are not null
    void buildProductReview() {
        ProductReview productReview = ProductReviewFactory.buildProductReview(productReviewID, productName, customerName, review, 5);
        assertNotNull(productReview);
        System.out.println(productReview);
    }

    @Test
    @Order(2)
    //This test will fail, as productName is null
    void buildProductReviewWithNullProductName() {
        ProductReview productReview = ProductReviewFactory.buildProductReview(productReviewID, null, customerName, review, 5);
        assertNotNull(productReview);
        System.out.println(productReview);
    }

    @Test
    @Order(3)
    //This test will fail, as rating is not in the valid range
    void buildProductReviewWithInvalidRating() {
        ProductReview productReview = ProductReviewFactory.buildProductReview(productReviewID, productName, customerName, review, 6);
        assertNotNull(productReview);
        System.out.println(productReview);
    }

}