package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.CartItemFactory;
import za.ac.cput.service.CartService;
import za.ac.cput.service.ProductService;
import za.ac.cput.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class CartControllerTest {

    @Autowired
    private CartController cartController;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    private Cart cart;

    private User user;
    private  Product product;

    private CartItem cartItem1, cartItem2, cartItem3;

    @BeforeEach
    void setUp() {
        user = userService.read(1L);
        product = productService.read(1L);
        // Create an initial cart without items
        cart = CartFactory.createCart(
                null,
                user,
                150.0

        );

        // Save the cart to generate a cartID
        cart = cartService.create(cart);

        // Create test CartItems for the saved cart
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

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cartItems.add(cartItem3);

        // Update the cart with CartItems and save it
        cart = new Cart.Builder()
                .setId(cart.getId())
                .setUser(cart.getUser())
                .setTotal(cart.getTotal())
                .build();

        cartService.update(cart);
    }

    @AfterEach
    void tearDown() {
        // Cleanup: Delete test cart
        if (cart != null && cart.getId() != null) {
            cartService.deleteByCartID(cart.getId());
        }
    }

    @Test
    @Order(1)
    void createCart() {
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

        // Act
        ResponseEntity<Cart> response = cartController.createCart(newCart);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newCart.getTotal(), response.getBody().getTotal());
        System.out.println(response.getBody());

        // Cleanup
        cartService.deleteByCartID(response.getBody().getId());
    }

    @Test
    @Order(2)
    void read() {
        // Act
        ResponseEntity<Cart> response = cartController.read(cart.getId());
        System.out.println(response.getBody());

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart.getId(), response.getBody().getId());
    }

    @Test
    @Order(3)
    void updateCart() {
        // Arrange
        Cart updatedCart = new Cart.Builder()
                .copy(cart)
                .setTotal(200.0)
                .build();

        // Act
        ResponseEntity<Cart> response = cartController.updateCart(cart.getId(), updatedCart);
        System.out.println(response.getBody());
        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200.0, response.getBody().getTotal());
    }

    @Test
    @Order(4)
    void deleteCart() {
        System.out.println((cart.getId()));
        // Act
        ResponseEntity<Void> response = cartController.deleteCart(cart.getId());

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that the cart is deleted by trying to read it
        ResponseEntity<Cart> readResponse = cartController.read(cart.getId());
        assertNull(readResponse.getBody());
    }

    @Test
    @Order(5)
    void getAllCarts() {
        // Act
        ResponseEntity<List<Cart>> response = cartController.getAllCarts();
        System.out.println(response.getBody());

        // Assert
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(6)
    void getCartsByUserID() {
        // Act
        ResponseEntity<List<Cart>> response = cartController.getCartsByUserID(cart.getUser().getId());

        System.out.println(response.getBody());

        // Assert
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//    @Test
//    @Order(7)
//    void getCartsByStatus() {
//        // Act
//        ResponseEntity<List<Cart>> response = cartController.getCartsByStatus("Pending");
//        System.out.println(response.getBody());
//        // Assert
//        assertNotNull(response.getBody());
//        assertFalse(response.getBody().isEmpty());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }

    @Test
    @Order(8)
    void getCartsByDateRange() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().minusDays(10);
        LocalDateTime endDate = LocalDateTime.now();
        // Act
        ResponseEntity<List<Cart>> response = cartController.getCartsByDateRange(startDate, endDate);
        System.out.println(response.getBody());
        // Assert
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//    @Test
//    @Order(9)
//    void getCartsByAddressID() {
//        // Act
//        ResponseEntity<List<Cart>> response = cartController.getCartsByAddressID(cart.getAddressID());
//        System.out.println(response.getBody());
//
//        // Assert
//        assertNotNull(response.getBody());
//        assertFalse(response.getBody().isEmpty());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }

    @Test
    @Order(10)
    void getCartsByTotalPriceGreaterThan() {
        // Act
        ResponseEntity<List<Cart>> response = cartController.getCartsByTotalPriceGreaterThan(50.0);
        System.out.println(response.getBody());

        // Assert
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
