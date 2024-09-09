package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartFactoryTest {

    @Test
    void createCart_success() {
        long cartID = 2l;
        long customerID = 1L;
        List<CartItem> cartItems = new ArrayList<>();
        double totalAmount = 200.00;

        // Create cart using factory
        Cart cart = CartFactory.createCart(cartID,customerID, cartItems, totalAmount);

        // Assertions
        assertNotNull(cart);
        assertEquals(customerID, cart.getCustomerID());
        assertEquals(cartItems, cart.getCartItems());
        assertEquals(totalAmount, cart.getTotalAmount());
    }

    @Test
    void createCart_nullItems() {

        // Create cart with null cartItems
        Cart cart = CartFactory.createCart(1L, null, null,200.00);

        // Assertion
        assertNotNull(cart);
        assertNull(cart.getCartItems());
    }
}
