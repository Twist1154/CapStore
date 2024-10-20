package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Product;

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
private Cart cart;
private Product product;

    @Test
    void buildCartItem() {
        // Create test CartItem with valid data
        CartItem cartItem = CartItemFactory.createCartItem(
                null,
                cart,
                product,
                20
        );

        // Ensure that the CartItem was created successfully and is not null
        assertNotNull(cartItem);

        // Additional assertions to ensure the cart item details are correct
        assertEquals(1L, cartItem.getId());
        assertEquals(12L, cartItem.getProduct().getId());
        assertEquals(20, cartItem.getQuantity());
    }

    @Test
    void buildCartItemWithInvalidData() {
        // Expecting IllegalArgumentException for invalid data
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CartItemFactory.createCartItem(
                    null,
                    null,
                    product,
                    5
            );
        });

        assertEquals("Product ID must not be null or empty.", thrown.getMessage());
    }
}
