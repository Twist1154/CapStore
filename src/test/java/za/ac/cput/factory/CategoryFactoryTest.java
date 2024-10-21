/**
 * E-commerce Web Application for selling T-shirts
 * CategoryFactoryTest.java
 * This class tests the CategoryFactory class
 * Author: Mthandeni Mbobo (218223579)
 * */

package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryFactoryTest {

    Long categoryId = null;
    String categoryName = "Men";

    @Test
    @Order(1)
    //This test will pass and print category, all parameters are not null
    void buildCategory() {
        Category category = CategoryFactory.buildCategory(null,
                categoryName
        );
        assertNotNull(category);
        System.out.println(category);
    }

    @Test
    @Order(2)
    //This test will print null, as categoryName is null
    void buildCategoryWithNullCategoryName() {
        Category category = CategoryFactory.buildCategory(categoryId,
                null);
        assertNull(category);
        System.out.println(category);
    }

}