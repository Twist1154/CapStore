package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.User;

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

    private Product product;
    private Cart cart;
    private CartItem cartItem;
    private User user;
    @Test
    public void testCreateCart() {
        product = new Product();
        user = new User();
        // Create test CartItems with valid data
        CartItem cartItem1 = CartItemFactory.createCartItem(
                null,
                cart,
                product,
                12
        );

        CartItem cartItem2 = CartItemFactory.createCartItem(
                null,
                cart,
                product,
                10
        );

        CartItem cartItem3 = CartItemFactory.createCartItem(
                null,
                cart,
                product,
                20
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


        Long addressID = 1L;
        double totalPrice = 150.0;
        String status = "Pending";
        LocalDate cartDate = LocalDate.now();

        // Build the Cart object
        Cart cart = CartFactory.createCart(
                null,
                user,
                5000.00
        );

        // Asserting that the created cart is not null
        assertNotNull(cart);

        // Additional assertions to ensure the cart details are correct
        assertEquals(cart, cart);
        assertEquals(user, cart.getUser());
        assertEquals(cartItems, cart.getCartItems());
        assertEquals(totalPrice, cart.getTotal());

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


        Cart cart = CartFactory.createCart(
                null,
                user,
                800.00
        );

        // Asserting that the created cart is not null but has no items
        assertNotNull(cart);
        assertTrue(cart.getCartItems().isEmpty());
        assertEquals(0.0, cart.getTotal());
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
            CartFactory.createCart(
                    null,
                    user,
                    700.00
            );
        });

        assertEquals("Total price must be positive and cart must contain at least one item.", thrown.getMessage());
    }
}
