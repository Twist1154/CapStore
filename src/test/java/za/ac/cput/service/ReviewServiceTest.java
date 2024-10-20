/**
 *
 * ReviewServiceTest.java
 * Test for the ReviewService
 * Author: Mthandeni Mbobo (218223579)
 * */

package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ReviewFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;
    private static Review review;
    private static Product product;
    private static User user;

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    void setup() {
        product = productService.read(1L);
        user = userService.read(1L);

        // Save the product and user entities first
        product = productService.create(product);
        user = userService.create(user);

        review = ReviewFactory.buildReview(null, product, user, "Not happy with this!", 3); //id auto generated
        assertNotNull(review);
        System.out.println("Review: " + review);
    }

    @Test
    @Order(2)
    void create() {
        Review created = reviewService.create(review);
        assertEquals(review.getId(), created.getId());
        System.out.println("Created review: " + created);
    }

    @Test
    @Order(3)
    void read() {
        Review read = reviewService.read(review.getId());
        assertNotNull(read);
        System.out.println("Read review: " + read);
    }

    @Test
    @Order(4)
    void update() {
        Review newReview = new Review.Builder().copy(review).setReview("I made a mistake, this thing is awful").setRating(1).build();
        Review updated = reviewService.update(newReview);
        assertEquals(newReview.getReview(), updated.getReview());
        System.out.println("Updated review: " + updated);
    }

    @Test
    @Order(5)
    @Disabled
    void delete() {
        boolean deleted = reviewService.delete(review.getId());
        assertTrue(deleted);
        System.out.println("Review deleted: " + deleted);
    }

    @Test
    @Order(6)
    void findAll() {
        System.out.println("All productReviews\n" + reviewService.findAll());
    }

    @Test
    @Order(7)
    void findByProductReviewId() {
        //System.out.println("Review by Id: " + productReviewService.findByProductReviewId(review.getId()));
        System.out.println("This is Product Review " +1L+ ": " + reviewService.findById(1L));
    }

    @Test
    @Order(8)
    void findByRating() {
        //System.out.println("Review by Rating: " + productReviewService.findByRating(review.getRating()));
        System.out.println("These are the Product Reviews with a rating of " +3+ ": " + reviewService.findByRating(3));
    }

    @Test
    @Order(9)
    void findByProduct_ProductId() {
        System.out.println("Review by Product Id " +13+ ": " + reviewService.findByProduct_ProductId(13L));
    }

    @Test
    @Order(10)
    void findByUser_UserID() {
        System.out.println("Review by User Id "  +2+ ": " + reviewService.findByUser_UserID(2L));
    }

}