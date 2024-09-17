package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.CartItem;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CartItemFactoryTest.java
 *
 * Unit tests for the CartItemFactory class.
 *
 * Author: Kinzonzi Mukoko
 * Student Num: 221477934
 * Date: 10-Sep-24
 */

class CartItemFactoryTest {

    @Test
    void buildCartItem() {
        // Create test CartItem with valid data
        CartItem cartItem = CartItemFactory.buildCartItem(
                1L,
                12L,
                12.00,
                null
        );

        // Ensure that the CartItem was created successfully and is not null
        assertNotNull(cartItem);

        // Additional assertions to ensure the cart item details are correct
        assertEquals(1L, cartItem.getId());
        assertEquals(12L, cartItem.getProductID());
        assertEquals(12.00, cartItem.getPrice());
    }

    @Test
    void buildCartItemWithInvalidData() {
        // Expecting IllegalArgumentException for invalid data
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CartItemFactory.buildCartItem(
                    null,
                    null,
                    -10.00,
                    null
            );
        });

        assertEquals("Product ID must not be null or empty.", thrown.getMessage());
    }
}
