package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Cart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CartFactoryTest.java
 *
 * Unit tests for the CartFactory class.
 *
 * Author: Kinzonzi Mukoko
 * Student Num: 221477934
 * Date: 10-Sep-24
 */

public class CartFactoryTest {

    @Test
    public void testCreateCart() {
        // Create test CartItems with valid data
        CartItem cartItem1 = CartItemFactory.buildCartItem(
                1L,
                12L,
                12.00,
                null
        );

        CartItem cartItem2 = CartItemFactory.buildCartItem(
                2L,
                5L,
                10.00,
                null
        );

        CartItem cartItem3 = CartItemFactory.buildCartItem(
                null,
                20L,
                20.00,
                null
        );

        // Ensure that the CartItems were created successfully and not null
        assertNotNull(cartItem1);
        assertNotNull(cartItem2);
        assertNotNull(cartItem3);

        // Add CartItems to a list
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cartItems.add(cartItem3);

        // Creating test data for the Cart object
        Long cartID = 1L;
        Long userID = 1L;
        Long addressID = 1L;
        double totalPrice = 150.0;
        String status = "Pending";
        LocalDate cartDate = LocalDate.now();

        // Build the Cart object
        Cart cart = CartFactory.buildCart(
                cartID,
                totalPrice,
                cartDate,
                cartItems
        );

        // Asserting that the created cart is not null
        assertNotNull(cart);

        // Additional assertions to ensure the cart details are correct
        assertEquals(cartID, cart.getId());
        assertEquals(userID, cart.getUserID());
        assertEquals(cartDate, cart.getCartDate());
        assertEquals(cartItems, cart.getCartItems());
        assertEquals(totalPrice, cart.getTotalPrice());

        // Print the created cart for debugging
        System.out.println(cart);
    }

    @Test
    public void testCreateCartWithEmptyItems() {
        // Creating test data for the Cart object with no cart items
        Long cartID = 2L;
        double totalPrice = 0.0; // Assuming 0 for no items
        String status = "Pending";
        LocalDate cartDate = LocalDate.now();

        // Build the Cart object with an empty cartItems list
        List<CartItem> emptyCartItems = new ArrayList<>();
        Cart cart = CartFactory.buildCart(
                cartID,
                totalPrice,
                cartDate,
                emptyCartItems
        );

        // Asserting that the created cart is not null but has no items
        assertNotNull(cart);
        assertTrue(cart.getCartItems().isEmpty());
        assertEquals(0.0, cart.getTotalPrice());
    }

    @Test
    public void testCreateCartWithNegativeTotalPrice() {
        // Creating test data for the Cart object with a negative total price
        Long cartID = 3L;
        double totalPrice = -50.0;
        String status = "Pending";
        LocalDate cartDate = LocalDate.now();

        // Add empty cart items for this test
        List<CartItem> cartItems = new ArrayList<>();

        // Expecting IllegalArgumentException due to negative total price
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CartFactory.buildCart(
                    cartID,
                    totalPrice,
                    cartDate,
                    cartItems
            );
        });

        assertEquals("Total price must be positive and cart must contain at least one item.", thrown.getMessage());
    }
}
