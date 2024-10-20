package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Images;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.SubCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductFactoryTest {
    private SubCategory subCategory, subCategory2;

    @Order(1)
    @Test
    void buildProduct() {
        subCategory = new SubCategory();
        subCategory2 = new SubCategory();
        Product product = ProductFactory.buildProduct(
                1L,
                "Golfer",
                "White Golf t-shirt. Slim fit.",
                150,
                30,
                List.of(subCategory,subCategory2),
                "path/to/image1.jpg",
                "path/to/image2.jpg",
                "path/to/image3.jpg",
                "path/to/image4.jpg"
        );
        assertNotNull(product);
        System.out.println("Build product: "+ product);
    }

    @Order(2)
    @Test
    void buildProductWithNegativePrice() {

        Product product = ProductFactory.buildProduct(
                1L,
                "Golfer",
                "White Golf t-shirt. Slim fit.",
                -150,
                30,
                List.of(subCategory,subCategory2),
                "path/to/image1.jpg",
                "path/to/image2.jpg",
                "path/to/image3.jpg",
                "path/to/image4.jpg"
        );
        assertNotNull(product);
        System.out.println("Build product: "+ product);
    }
}