package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.ProductReview;
import za.ac.cput.factory.ProductReviewFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductReviewControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/shopping_store/productReview";
    private static ProductReview productReview;
    private static ProductReview productReview2;  // Store this to reuse in tests

    @BeforeAll
    public static void setup() {
        productReview = ProductReviewFactory.buildProductReview(null, "Box Fit Unified T-shirt - UC Saw Dust/Geants De Monaco", "Siya Mthandeni", "Great product!", 5);
        productReview2 = ProductReviewFactory.buildProductReview(null, "Box Fit Unified T-shirt ", "Lilitha Mbobo", "Great product", 5);
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<ProductReview> postResponse = restTemplate.postForEntity(url, productReview, ProductReview.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        productReview = postResponse.getBody();  // Capture and reuse the created product review
        assertNotNull(productReview.getProductReviewId());  // Ensure ID is not null after creation
        System.out.println("Create: " + productReview);

        ResponseEntity<ProductReview> postResponse2 = restTemplate.postForEntity(url, productReview2, ProductReview.class);
        assertNotNull(postResponse2);
        assertNotNull(postResponse2.getBody());
        productReview2 = postResponse2.getBody();  // Capture and reuse the created product review
        assertNotNull(productReview2.getProductReviewId());  // Ensure ID is not null after creation
        System.out.println("Create2: " + productReview2);
    }

    @Test
    @Order(2)
    void read() {
        String url2 = BASE_URL + "/read/" + productReview2.getProductReviewId();
        System.out.println("\nURL: " + url2);
        ResponseEntity<ProductReview> response2 = restTemplate.getForEntity(url2, ProductReview.class);
        assertNotNull(response2);
        assertNotNull(response2.getBody());
        assertEquals(productReview2.getProductReviewId(), response2.getBody().getProductReviewId());  // Compare IDs
        System.out.println("Read2: " + response2.getBody());

        String url = BASE_URL + "/read/" + productReview.getProductReviewId();
        System.out.println("\nURL: " + url);
        ResponseEntity<ProductReview> response = restTemplate.getForEntity(url, ProductReview.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(productReview.getProductReviewId(), response.getBody().getProductReviewId());  // Compare IDs
        System.out.println("Read: " + response.getBody());
    }

    @Test
    @Order(3)
    void update() {
        // Create a copy of productReview2 with the updated review
        ProductReview updated = new ProductReview.Builder().copy(productReview2)
                .setReview("Updated Review")
                .build();  // Make sure productReviewID is set

        String url = BASE_URL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Updated: " + updated);

        // Use PUT instead of POST for the update
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ProductReview> entity = new HttpEntity<>(updated, headers);
        ResponseEntity<ProductReview> response = restTemplate.exchange(url, HttpMethod.PUT, entity, ProductReview.class);

        // Verify the response
        assertNotNull(response.getBody());
        assertEquals("Updated Review", response.getBody().getReview());  // Check if the review was updated successfully
    }

    @Test
    @Order(4)
     void delete() {
         String url = BASE_URL + "/delete/" + productReview2.getProductReviewId();  // Ensure correct ID is passed
         System.out.println("URL: " + url);
         restTemplate.delete(url);
            System.out.println("Product review deleted: " + productReview2.getProductReviewId());
     }

    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getAll";
        System.out.println("URL: " + url);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Get all: ");
        System.out.println(response);
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
    }

//    @Test
//    @Order(6)
//    void getByRating() {
//    }
//
//    @Test
//    @Order(7)
//    void findByProductReviewId() {
//    }
//
//    @Test
//    @Order(8)
//    void findByCustomerName() {
//    }
//
//    @Test
//    @Order(9)
//    void findByProductName() {
//    }
}