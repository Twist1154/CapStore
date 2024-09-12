package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private Product product;

    @BeforeEach
    void setUp() {
        // Initialize a Product object before each test
        String image = "path/to/image.jpg";
        List<String> images = new ArrayList<>();
        images.add(image);

        product = new Product.Builder()
                .setProductId(1L)
                .setName("Golfer t-shirt")
                .setDescription("Black medium shirt.")
                .setPrice(200)
                .setStock(10)
                .setCategoryId(1L)
                .setCreatedAt(LocalDate.now().atStartOfDay())
                .setUpdatedAt(LocalDate.now().atStartOfDay())
                .setImagePath(images)
                .build();
    }

    @Order(1)
    @Test
    void createProduct() {
        // Test creating a new product
        ResponseEntity<Product> response = restTemplate.postForEntity("/product/create", product, Product.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(product.getName(), response.getBody().getName());
        System.out.println(response.getBody());
    }

    @Order(2)
    @Test
    void readProduct() {
        // Test retrieving a product by its ID
//        ResponseEntity<Product> response = restTemplate.getForEntity("/product/read"+ product.getProductId(), Product.class);
        ResponseEntity<Product> response = restTemplate.getForEntity("/product/read"+ product.getProductId(), Product.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(product.getProductId(), response.getBody().getProductId());
        System.out.println("Product read: "+ response.getBody());
    }

    @Order(3)
    @Test
    void updateProduct() {
        // Test updating an existing product
        Product createdProduct = restTemplate.postForObject("/store/api/products", product, Product.class);
        createdProduct = new Product.Builder().copy(createdProduct).setName("Updated Product").build();
        HttpEntity<Product> requestUpdate = new HttpEntity<>(createdProduct);
        ResponseEntity<Product> response = restTemplate.exchange("/store/api/products", HttpMethod.PUT, requestUpdate, Product.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Product", response.getBody().getName());
    }

    @Order(5)
    @Test
    void deleteProduct() {
        // Test deleting a product
        Product createdProduct = restTemplate.postForObject("/store/api/products", product, Product.class);
        restTemplate.delete("/store/api/products/" + createdProduct.getProductId());
        ResponseEntity<Product> response = restTemplate.getForEntity("/store/api/products/" + createdProduct.getProductId(), Product.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Order(4)
    @Test
    void getAllProducts() {
        // Test retrieving all products
        ResponseEntity<List<Product>> response = restTemplate.exchange("/product/getAll", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        );
        assertNotNull(response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        System.out.println("Products list: "+ response.getBody());
    }

    @AfterEach
    void tearDown() {
        // Cleanup after each test if necessary
    }
}
