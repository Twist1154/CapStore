package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.OnlineClothingStoreApp;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CartItemFactory;
import za.ac.cput.service.CartItemService;
import za.ac.cput.service.CartService;
import za.ac.cput.service.ProductService;
import za.ac.cput.service.UserService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {OnlineClothingStoreApp.class})
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartItemControllerTest {

    @Autowired
    private CartItemController cartItemController;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    private Product product;

    private User user;
    private Cart cart;
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        user = userService.read(1L);
        product = productService.read(1L);
        // Setup a test cart
        cart = new Cart.Builder()
                .setId(null)
                .setUser(user)
                .setTotal(100.0)
                .build();
        cart = cartService.create(cart); // Save cart to get an ID

        // Create a new cart item associated with the saved cart
        cartItem = CartItemFactory.createCartItem(
                null,
                cart,
                product,
                5
        );

        // Save the cartItem to generate a cartItemID
        cartItem = cartItemService.create(cartItem);
    }

    @AfterEach
    void tearDown() {
        // Cleanup: Deleting the test cartItem from the database
        if (cartItem != null && cartItem.getId() != null) {
            cartItemService.deleteById(cartItem.getId());
        }
    }

    @Test
    @Order(1)
    void createCartItem() {
        // Arrange
        CartItem newCartItem = CartItemFactory.createCartItem(
                null,
                cart,
                product,
                23
        );

        // Act
        ResponseEntity<CartItem> response = cartItemController.createCartItem(newCartItem);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Output for confirmation
        System.out.println(response.getBody());
    }

    @Test
    @Order(2)
    void readCartItem() {
        // Act
        ResponseEntity<CartItem> response = cartItemController.getCartItemById(cartItem.getId());

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartItem.getId(), response.getBody().getId());
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    void updateCartItem() {
        // Arrange
        CartItem updatedCartItem = new CartItem.Builder()
                .copy(cartItem)
                .build();

        // Act
        ResponseEntity<CartItem> response = cartItemController.updateCartItem(cartItem.getId(), updatedCartItem);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    @Order(4)
    void deleteCartItem() {
        // Act
        ResponseEntity<Void> response = cartItemController.deleteCartItem(cartItem.getId());

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that the cartItem is deleted by trying to read it again
        ResponseEntity<CartItem> readResponse = cartItemController.getCartItemById(cartItem.getId());
        assertNull(readResponse.getBody());
    }

    @Test
    @Order(5)
    void getAllCartItems() {
        // Act
        ResponseEntity<List<CartItem>> response = cartItemController.getAllCartItems();

        System.out.println(response.getBody());

        // Assert
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Output for confirmation
        System.out.println(response.getBody());
    }
}
