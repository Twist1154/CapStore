package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Cart;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.CartItemFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    private Cart cart;

    @BeforeEach
    void setUp() {
        // Create an initial cart
        cart = CartFactory.buildCart(
                null,
                150.0,
                LocalDate.now(),
                new ArrayList<>()
        );

        // Save the cart to generate a cartID
        cart = cartService.create(cart);

        // Create CartItems
        CartItem cartItem1 = CartItemFactory.buildCartItem(
                null,
                cart.getId(),
                12.00,
                cart
        );

        CartItem cartItem2 = CartItemFactory.buildCartItem(
                null,
                cart.getId(),
                10.00,
                cart
        );

        CartItem cartItem3 = CartItemFactory.buildCartItem(
                null,
                cart.getId(),
                20.00,
                cart
        );

        // Save CartItems
        cartItem1 = cartItemService.create(cartItem1);
        cartItem2 = cartItemService.create(cartItem2);
        cartItem3 = cartItemService.create(cartItem3);

        // Ensure CartItems are associated with the Cart
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cartItems.add(cartItem3);

        cart = new Cart.Builder()
                .copy(cart)
                .setCartItems(cartItems)  // Add CartItems
                .build();

        cart = cartService.update(cart); // Ensure the updated cart is persisted
    }

    @Test
    @Order(1)
    void create() {
        Cart newCart = CartFactory.buildCart(
                null,
                200.0,
                LocalDate.now(),
                new ArrayList<>()
        );

        Cart createdCart = cartService.create(newCart);
        assertNotNull(createdCart);
        assertNotNull(createdCart.getId());  // Check if ID is generated
        System.out.println("Created: \n" + createdCart + "\n");
    }

    @Test
    @Order(2)
    void read() {
        Cart readCart = cartService.read(cart.getId());
        assertNotNull(readCart);
        assertEquals(cart.getId(), readCart.getId());
        System.out.println("Read: \n" + readCart + "\n");

        // Debugging CartItems
        List<CartItem> cartItems = readCart.getCartItems();
        System.out.println("Cart Items: " + cartItems);
    }

    @Test
    @Order(3)
    void update() {
        Cart updatedCart = new Cart.Builder()
                .copy(cart)
                .setUserID(3L)
                .setTotalPrice(300.0)
                .build();

        Cart result = cartService.update(updatedCart);
        assertNotNull(result);
        assertEquals(updatedCart.getTotalPrice(), result.getTotalPrice());

        System.out.println("Updated: \n" + result + "\n");
    }

    @Test
    @Order(4)
    void getAll() {
        List<Cart> carts = cartService.findAll();
        assertFalse(carts.isEmpty());
        System.out.println("All Carts: \n" + carts + "\n");
    }

    @Test
    @Order(5)
    void delete() {
        Long cartIdToDelete = cart.getId();
        cartService.deleteByCartID(cartIdToDelete);

        Cart deletedCart = cartService.read(cartIdToDelete);
        assertNull(deletedCart);
        System.out.println("Deleted Cart ID: " + cartIdToDelete);
    }
}
