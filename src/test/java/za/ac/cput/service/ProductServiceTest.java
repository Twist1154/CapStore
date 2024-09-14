package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Images;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.ProductFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    private static Product product1, product2, product3;

    @BeforeEach
    void setUp() {
        // Create product objects with individual image URLs using the ProductFactory
        product1 = ProductFactory.buildProduct(
                1L,
                "Golfer t-shirt",
                "Black medium shirt.",
                200,
                10,
                1L,
                LocalDate.now().atStartOfDay(),
                LocalDate.now().atStartOfDay(),
                "path/to/image1.jpg", "path/to/image2.jpg", "path/to/image3.jpg", "path/to/image4.jpg"
        );

        product2 = ProductFactory.buildProduct(
                2L,
                "V-neck t-shirt",
                "Blue slim fit shirt.",
                150,
                15,
                1L,
                LocalDate.now().atStartOfDay(),
                LocalDate.now().atStartOfDay(),
                "path/to/image1.jpg",
                "path/to/image2.jpg",
                "path/to/image3.jpg",
                "path/to/image4.jpg"
        );

        product3 = ProductFactory.buildProduct(
                3L,
                "Crew neck t-shirt",
                "Green slim fit shirt.",
                125,
                20,
                1L,
                LocalDate.now().atStartOfDay(),
                LocalDate.now().atStartOfDay(),
                "path/to/image1.jpg",
                "path/to/image2.jpg",
                "path/to/image3.jpg",
                "path/to/image4.jpg"
        );
    }

    @Order(1)
    @Test
    void create() {
        Product createdProduct1 = productService.create(product1);
        assertNotNull(createdProduct1);
        System.out.println("Product 1: " + createdProduct1);

        Product createdProduct2 = productService.create(product2);
        assertNotNull(createdProduct2);
        System.out.println("Product 2: " + createdProduct2);

        Product createdProduct3 = productService.create(product3);
        assertNotNull(createdProduct3);
        System.out.println("Product 3: " + createdProduct3);
    }

    @Order(2)
    @Test
    void read() {
        Product readProduct1 = productService.read(1L);
        assertNotNull(readProduct1);
        assertEquals(1L, readProduct1.getProductId());
        System.out.println("Read product 1: " + readProduct1);
    }

    @Order(3)
    @Test
    void update() {
        System.out.println("Product 2 before update: " + product2);
        Images images = new Images(
                "updated/path/to/image1.jpg",
                "path/to/image2.jpg",
                "path/to/image3.jpg",
                "path/to/image4.jpg"
        );

        // Update the description and one of the image URLs
        Product updatedProduct2 = new Product.Builder().copy(product2)
                .setDescription("Blue V-neck t-shirt. Slim fit, medium.")
                .setStock(10)
                .setImages(images)
                .build();

        Product result = productService.update(updatedProduct2);
        assertNotNull(result);
        assertEquals("Blue V-neck t-shirt. Slim fit, medium.", result.getDescription());
        assertEquals("updated/path/to/image1.jpg", result.getImages().getImageUrl1());
        System.out.println("Updated product 2: " + result);
    }

    @Order(5)
    @Test
    void delete() {
        System.out.println("Product to be deleted: " + product3);
        Long product3Id = product3.getProductId();
        productService.delete(product3Id);

        Product deletedProduct = productService.read(product3Id);
        assertNull(deletedProduct);
        System.out.println("Deleted status: " + deletedProduct);
    }

    @Order(4)
    @Test
    void findAll() {
        List<Product> productList = productService.findAll();
        assertFalse(productList.isEmpty());
        System.out.println(productList);
    }
}
