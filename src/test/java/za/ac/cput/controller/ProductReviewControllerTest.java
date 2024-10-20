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
import za.ac.cput.domain.ProductReview;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ProductReviewFactory;
import za.ac.cput.service.ProductService;
import za.ac.cput.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductReviewControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/shopping_store/productReview";
    private static ProductReview productReview;
    private static Product product;
    private static User user;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @BeforeAll
    public static void setup(@Autowired ProductService productService, @Autowired UserService userService) {
        // Save the product and user entities first
        product = new Product.Builder()
                .setId(null)
                .setName("Test Product")
                .build();
        product = productService.create(product);

        user = new User.Builder()
                .setId(null)
                .setFirstName("Mthandeni")
                .setLastName("Mbobo")
                .build();
        user = userService.create(user);

        productReview = ProductReviewFactory.buildProductReview(null, product, user, "Great product!", 5);
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        System.out.println("URL: " + url);
        ResponseEntity<ProductReview> postResponse = restTemplate.postForEntity(url, productReview, ProductReview.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        productReview = postResponse.getBody();  // Capture and reuse the created product review
        assertNotNull(productReview.getProductReviewId());  // Ensure ID is not null after creation
        System.out.println("Create: " + productReview);
    }


    @Test
    @Order(2)
    void read() {
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
        // Create a copy of productReview with the updated review
        ProductReview updated = new ProductReview.Builder().copy(productReview)
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
    @Disabled
    @Order(4)
     void delete() {
         String url = BASE_URL + "/delete/" + productReview.getProductReviewId();  // Ensure correct ID is passed
         System.out.println("URL: " + url);
         restTemplate.delete(url);
            System.out.println("Product review deleted: " + productReview.getProductReviewId());
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

    @Test
    @Order(6)
    void getByRating() {
        String url = BASE_URL + "/rating/5";
        System.out.println("URL: " + url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("Get by rating: ");
        System.out.println(response);
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(7)
    void findByProductReviewId() {
        String url = BASE_URL + "/find/" + productReview.getProductReviewId();
        System.out.println("URL: " + url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("Find by ID: ");
        System.out.println(response);
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(8)
    void findByProductId_ProductId() {
        String url = BASE_URL + "/product/" + product.getId();
        System.out.println("URL: " + url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("Find by product ID: ");
        System.out.println(response);
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(9)
    void findByUserId_UserID() {
        String url = BASE_URL + "/user/" + user.getUserID();
        System.out.println("URL: " + url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("Find by user ID: ");
        System.out.println(response);
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
    }
}