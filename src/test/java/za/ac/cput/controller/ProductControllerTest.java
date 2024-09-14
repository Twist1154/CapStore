package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.OnlineClothingStoreApp;
import za.ac.cput.domain.Images;
import za.ac.cput.domain.Product;
import za.ac.cput.service.ProductService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {OnlineClothingStoreApp.class})
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        Images images = new Images(
                "path/Controller/image1.jpg",
                "path/Controller/image2.jpg",
                "path/Controller/image3.jpg",
                "path/Controller/image4.jpg"
        );

        product = new Product.Builder()
                .setProductId(null)  // Set to null for ID to be generated
                .setName("Golfer t-shirt")
                .setDescription("Black medium shirt.")
                .setPrice(200)
                .setStock(10)
                .setCategoryId(1L)
                .setCreatedAt(LocalDate.now().atStartOfDay())
                .setUpdatedAt(LocalDate.now().atStartOfDay())
                .setImages(images)
                .build();

        // Save the product to generate an ID for subsequent tests
        product = productService.create(product);
    }

    @AfterEach
    void tearDown() {
        // Optionally delete the created product
//        if (product != null && product.getProductId() != null) {
//            productService.deleteById(product.getProductId());
//        }
    }

    @Test
    @Order(1)
    void createProduct() {
        // Arrange
        Product newProduct = new Product.Builder()
                .setProductId(null)  // ID will be generated
                .setName("New Product")
                .setDescription("Description for the new product.")
                .setPrice(300)
                .setStock(5)
                .setCategoryId(1L)
                .setCreatedAt(LocalDate.now().atStartOfDay())
                .setUpdatedAt(LocalDate.now().atStartOfDay())
                .setImages(new Images(
                        "newPath/image1.jpg",
                        "newPath/image2.jpg",
                        "newPath/image3.jpg",
                        "newPath/image4.jpg"))
                .build();

        // Act
        ResponseEntity<Product> response = productController.createProduct(newProduct);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newProduct.getName(), response.getBody().getName());

        // Output for confirmation
        System.out.println("Created Product: " + response.getBody());
    }

    @Test
    @Order(2)
    void readProduct() {
        // Act
        ResponseEntity<Product> response = productController.getProductById(product.getProductId());

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product.getProductId(), response.getBody().getProductId());
        System.out.println("Product read: " + response.getBody());
    }

    @Test
    @Order(3)
    void updateProduct() {
        // Arrange
        Product updatedProduct = new Product.Builder()
                .copy(product)
                .setName("Updated Product Name")
                .setDescription("Updated Product Description")
                .build();

        // Act
        ResponseEntity<Product> response = productController.updateProduct(product.getProductId(), updatedProduct);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Product Name", response.getBody().getName());
        System.out.println("Updated Product: " + response.getBody());
    }

    @Test
    @Order(4)
    void deleteProduct() {
        // Act
        ResponseEntity<Void> response = productController.deleteProduct(product.getProductId());

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that the product is deleted by trying to read it again
        ResponseEntity<Product> readResponse = productController.getProductById(product.getProductId());
        assertNull(readResponse.getBody());
    }

    @Test
    @Order(5)
    void getAllProducts() {
        // Act
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Assert
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Output for confirmation
        System.out.println("Products list: " + response.getBody());
    }
}
