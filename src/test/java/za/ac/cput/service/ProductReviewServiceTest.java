/**
 *
 * ProductReviewServiceTest.java
 * Test for the ProductReviewService
 * Author: Mthandeni Mbobo (218223579)
 * */

package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductReview;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ProductReviewFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductReviewServiceTest {

    @Autowired
    private ProductReviewService productReviewService;
    private static ProductReview productReview;

    Product product = new Product.Builder()
            .setProductId(null)
            .setName("Test Product")
            .build();

    User user = new User.Builder()
            .setId(null)
            .setFirstName("Mthandeni")
            .setLastName("Mbobo")
            .build();

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    void setup() {
        // Save the product and user entities first
        product = productService.create(product);
        user = userService.create(user);

        productReview = ProductReviewFactory.buildProductReview(null, product, user, "Not happy with this!", 3); //id auto generated
        assertNotNull(productReview);
        System.out.println("ProductReview: " + productReview);
    }

    @Test
    @Order(2)
    void create() {
        ProductReview created = productReviewService.create(productReview);
        assertEquals(productReview.getProductReviewId(), created.getProductReviewId());
        System.out.println("Created productReview: " + created);
    }

    @Test
    @Order(3)
    void read() {
        ProductReview read = productReviewService.read(productReview.getProductReviewId());
        assertNotNull(read);
        System.out.println("Read productReview: " + read);
    }

    @Test
    @Order(4)
    void update() {
        ProductReview newProductReview = new ProductReview.Builder().copy(productReview).setReview("I made a mistake, this thing is awful").setRating(1).build();
        ProductReview updated = productReviewService.update(newProductReview);
        assertEquals(newProductReview.getReview(), updated.getReview());
        System.out.println("Updated productReview: " + updated);
    }

    @Test
    @Order(5)
    @Disabled
    void delete() {
        boolean deleted = productReviewService.delete(productReview.getProductReviewId());
        assertTrue(deleted);
        System.out.println("ProductReview deleted: " + deleted);
    }

    @Test
    @Order(6)
    void findAll() {
        System.out.println("All productReviews\n" + productReviewService.findAll());
    }

    @Test
    @Order(7)
    void findByProductReviewId() {
        //System.out.println("ProductReview by Id: " + productReviewService.findByProductReviewId(productReview.getProductReviewId()));
        System.out.println("This is Product Review " +1L+ ": " + productReviewService.findByProductReviewId(1L));
    }

    @Test
    @Order(8)
    void findByRating() {
        //System.out.println("ProductReview by Rating: " + productReviewService.findByRating(productReview.getRating()));
        System.out.println("These are the Product Reviews with a rating of " +3+ ": " + productReviewService.findByRating(3));
    }

    @Test
    @Order(9)
    void findByProduct_ProductId() {
        System.out.println("ProductReview by Product Id " +13+ ": " + productReviewService.findByProduct_ProductId(13L));
    }

    @Test
    @Order(10)
    void findByUser_UserID() {
        System.out.println("ProductReview by User Id "  +2+ ": " + productReviewService.findByUser_UserID(2L));
    }

}