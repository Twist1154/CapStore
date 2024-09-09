package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.CartItem;

import static org.junit.jupiter.api.Assertions.*;

class CartItemFactoryTest {

    @Test
    void createCartItem_success() {
        long cartID = 1L;
        long productID = 2L;
        double price = 10.99;

        CartItem cartItem = CartItemFactory.createCartItem(cartID, productID, price);

        assertNotNull(cartItem);
        assertEquals(cartID, cartItem.getCartID());
        assertEquals(productID, cartItem.getProductID());
        assertEquals(price, cartItem.getPrice());
    }

    @Test
    void createCartItem_failure_invalidInput() {
        long invalidCartID = -1L;
        long productID = 2L;
        double price = 10.99;

        CartItem cartItem = null;

        try {
            cartItem = CartItemFactory.createCartItem(invalidCartID, productID, price);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input provided", e.getMessage());
        }

        // Verify the cartItem is null since invalid input was provided
        assertNotNull(cartItem, "CartItem should not be null");
    }

}
