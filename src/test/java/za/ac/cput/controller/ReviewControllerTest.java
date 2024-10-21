/**
 * ReviewControllerTest.java
 * Test for the ReviewController
 *
 * Author: Mthandeni Mbobo (218223579)
 * */

package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.service.ProductService;
import za.ac.cput.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/shopping_store/review";
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
        String url = BASE_URL + "/create";
        ResponseEntity<Review> postResponse = restTemplate.postForEntity(url, review, Review.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        review = postResponse.getBody();
        assertNotNull(review.getId());
        System.out.println("Created review: " + review);
    }

    @Test
    @Order(3)
    void read() {
        String url = BASE_URL + "/read/" + review.getId();
        ResponseEntity<Review> response = restTemplate.getForEntity(url, Review.class);
        assertNotNull(response.getBody());
        System.out.println("Read review: " + response.getBody());
    }

    @Test
    @Order(4)
    void update() {
        Review updated = new Review.Builder().copy(review).setReview("Updated Review").build();
        String url = BASE_URL + "/update";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Review> entity = new HttpEntity<>(updated, headers);
        ResponseEntity<Review> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Review.class);
        assertNotNull(response.getBody());
        assertEquals("Updated Review", response.getBody().getReview());
        System.out.println("Updated review: " + response.getBody());
    }

    @Test
    @Order(5)
    @Disabled
    void delete() {
        String url = BASE_URL + "/delete/" + review.getId();
        restTemplate.delete(url);
        System.out.println("Review deleted: " + review.getId());
    }

    @Test
    @Order(6)
    void getAll() {
        String url = BASE_URL + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        System.out.println("All reviews: " + response.getBody());
    }

    @Test
    @Order(7)
    void getByRating() {
        String url = BASE_URL + "/getByRating/" + review.getRating();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertNotNull(response.getBody());
        System.out.println("Reviews with rating " + review.getRating() + ": " + response.getBody());
    }

    @Test
    @Order(8)
    void findByProductId() {
        String url = BASE_URL + "/getById/" + review.getId();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertNotNull(response.getBody());
        System.out.println("Review by ID: " + response.getBody());
    }

    @Test
    @Order(9)
    void findByProductId_ProductId() {
        String url = BASE_URL + "/getByProductId/" + product.getProductId();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertNotNull(response.getBody());
        System.out.println("Reviews by product ID: " + response.getBody());
    }

    @Test
    @Order(10)
    void findByUserId_UserID() {
        String url = BASE_URL + "/getByUserId/" + user.getUserID();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertNotNull(response.getBody());
        System.out.println("Reviews by user ID: " + response.getBody());
    }
}