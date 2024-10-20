package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ReviewFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewServiceTest {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    ProductService productService;
    @Autowired
    private UserService userService;
    private ReviewFactory reviewFactory;
    private Review review;
    private Product product;
    private User user;

    @BeforeEach
    void setUp() {
        product = productService.read(1L);
        user = userService.read(1L);

        Review saveReview = ReviewFactory.buildReview(
                null,
                product,
                user,
                5,
                "Great product",
                null
        );
        review = reviewService.create(saveReview);
    }

    @Test
    void create() {
        Review created = reviewService.create(review);
        assertEquals(review.getId(), created.getId());
        System.out.println("Created: " + created);
    }

    @Test
    void read() {
        Review read = reviewService.read(review.getId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void update() {
        Review updated = new Review.Builder()
                .copy(review)
                .setRating(4)
                .build();

        assertNotNull(reviewService.update(updated));
        System.out.println("Updated: " + updated);
    }

    @Test
    void findAll() {
        List<Review> reviews = reviewService.findAll();
        assertNotNull(reviews);
    }

    @Test
    void deleteById() {
        reviewService.delete(review.getId());
        assertNull(reviewService.read(review.getId()));
    }

    @Test
    void findByProduct_Id() {
        List<Review> review = reviewService.findByProduct_Id(product.getId());
        assertNotNull(review);
        System.out.println("Review: " + review);
    }

    @Test
    void findByUser_Id() {
        List<Review> review = reviewService.findByUser_Id(product.getId());
        assertNotNull(review);
        System.out.println("Review: " + review);
    }
}