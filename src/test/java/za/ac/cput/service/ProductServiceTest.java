package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.ProductFactory;

import java.time.LocalDate;
import java.util.ArrayList;
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
//        product1 = new Product.Builder()
//                .setProductId(1L)
//                .setName("Golfer t-shirt")
//                .setDescription("Black medium shirt.")
//                .setPrice(200)
//                .setStock(10)
//                .setCategoryId(1L)
//                .setCreatedAt(LocalDate.now().atStartOfDay())
//                .setUpdatedAt(LocalDate.now().atStartOfDay())
//                .setImagePath("path/to/image.jpg")
//                .build();
        String image = "path/to/image.jpg";
        List<String> images = new ArrayList<>();
        images.add(image);

        product1 = ProductFactory.buildProduct(
                1L,
                "Golfer t-shirt",
                "Black medium shirt.",
                200,
                10,
                1L,
                LocalDate.now().atStartOfDay(),
                LocalDate.now().atStartOfDay(),
                images
        );

//        product2 = new Product.Builder()
//                .setProductId(1L)
//                .setName("V-neck t-shirt")
//                .setDescription("Blue slim fit shirt.")
//                .setPrice(150)
//                .setStock(15)
//                .setCategoryId(1L)
//                .setCreatedAt(LocalDate.now().atStartOfDay())
//                .setUpdatedAt(LocalDate.now().atStartOfDay())
//                .setImagePath("path/to/image.jpg")
//                .build();

        product2 = ProductFactory.buildProduct(
                1L,
                "V-neck t-shirt",
                "Blue slim fit shirt",
                150,
                15,
                1L,
                LocalDate.now().atStartOfDay(),
                LocalDate.now().atStartOfDay(),
                images
        );

//        product3 = new Product.Builder()
//                .setProductId(1L)
//                .setName("Crew neck t-shirt")
//                .setDescription("Green slim fit shirt")
//                .setPrice(125)
//                .setStock(20)
//                .setCategoryId(1L)
//                .setCreatedAt(LocalDate.now().atStartOfDay())
//                .setUpdatedAt(LocalDate.now().atStartOfDay())
//                .setImagePath("path/to/image.jpg")
//                .build();

        product3 = ProductFactory.buildProduct(
                1L,
                "Crew neck t-shirt",
                "Green slim fit shirt",
                125,
                20,
                1L,
                LocalDate.now().atStartOfDay(),
                LocalDate.now().atStartOfDay(),
                images
        );
    }

    @Order(1)
    @Test
    void create() {
        Product createdProduct1 = productService.create(product1);
        assertNotNull(createdProduct1);
        System.out.println("Product 1: "+ createdProduct1);

        Product createdProduct2 = productService.create(product2);
        assertNotNull(createdProduct2);
        System.out.println("Product 2: "+ createdProduct2);

        Product createdProduct3 = productService.create(product3);
        assertNotNull(createdProduct3);
        System.out.println("Product 3: "+ createdProduct3);
    }

    @Order(2)
    @Test
    void read() {
        Product readProduct1 = productService.read(2L);
        assertNotNull(readProduct1);
        assertEquals(2L, readProduct1.getProductId());
        System.out.println("Read product 1:"+ readProduct1);
    }

    @Order(3)
    @Test
    void update() {
        System.out.println("Product 2 before update: "+ product2);
        String image = "path/to/image.jpg";
        List<String> images = new ArrayList<>();
        images.add(image);

//        Product updateProduct2 = new Product.Builder()
//                .setProductId(product2.getProductId())
//                .setName("V-neck t-shirt")
//                .setDescription("Blue V-neck t-shirt. Slim fit, medium. ") //Updated description.
//                .setPrice(150)
//                .setStock(10) // Updated stock.
//                .setCategoryId(1L)
//                .setCreatedAt(LocalDate.now().atStartOfDay())
//                .setUpdatedAt(LocalDate.now().atStartOfDay())
//                .setImagePath(images)
//                .build();
        Product updateProduct2 = new Product.Builder().copy(product2)
                .setDescription("Blue V-neck t-shirt. Slim fit, medium. ")
                .setStock(10)
                .build();

        Product result = productService.update(updateProduct2);
//        assertNotNull(result);
        assertEquals(updateProduct2.getDescription(), result.getDescription());
        System.out.println(result);
    }

    @Order(5)
    @Test
    void delete() {
        System.out.println("Product to be deleted: "+ product3);
        Long product3Id = product3.getProductId();
        productService.delete(product3Id);

        Product deleteProduct = productService.read(product3Id);
        assertNull(deleteProduct);
        System.out.println("Deleted status:"+ deleteProduct);
    }

    @Order(4)
    @Test
    void findAll() {
        List<Product> productList = productService.findAll();
        assertFalse(productList.isEmpty());
        System.out.println(productList);
    }
}