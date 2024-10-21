package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.User;
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

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    private Cart cart;
    private User user;
    private Product product;
    private CartItem cartItem, cartItem1, cartItem2, cartItem3;

    @BeforeEach
    void setUp() {
        user = userService.read(1L);
        // Create an initial cart
        cart = CartFactory.createCart(
                null,
                user,
                150.0
        );

        // Save the cart to generate a cartID
        cart = cartService.create(cart);

        // Create CartItems
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
                .setTotal(800.00)
                .build();

        cart = cartService.update(cart); // Ensure the updated cart is persisted
    }

    @Test
    @Order(1)
    void create() {
        // Arrange
        List<CartItem> cartItems = List.of(
                cartItem1 = CartItemFactory.createCartItem(
                        null,
                        cart,
                        product,
                        12
                ),

                cartItem2 = CartItemFactory.createCartItem(
                        null,
                        cart,
                        product,
                        10
                ),

                cartItem3 = CartItemFactory.createCartItem(
                        null,
                        cart,
                        product,
                        20
                )
        );

        Cart newCart = CartFactory.createCart(
                null,
                user,
                150.0
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
                .setUser(user)
                .setTotal(300.0)
                .build();

        Cart result = cartService.update(updatedCart);
        assertNotNull(result);
        assertEquals(updatedCart.getTotal(), result.getTotal());

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
