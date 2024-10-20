package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.User;
import za.ac.cput.repository.ICartItemRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartItemServiceTest {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ICartItemRepository cartItemRepository;

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    private static Cart testCart;
    private User user;
    private ProductService productService;
    private Product product;

    @BeforeEach
    void setUp() {
        user = userService.read(1L);
        product = productService.read(1L);
        // Setup a test cart
        testCart = new Cart.Builder()
                .setId(55L)
                .setUser(user)
                .setTotal(100.0)
                .build();
        testCart = cartService.create(testCart); // Save cart to get an ID
    }

    @AfterEach
    void tearDown() {
        // Clean up data after each test
        cartItemRepository.deleteAll();
        cartService.deleteByCartID(testCart.getId()); // Clean up test cart
    }

    @Test
    @Order(1)
    void create() {
        System.out.println("Create");
        CartItem cartItem = new CartItem.Builder()
                .setId(2L)
                .setProduct(product)
                .setQuantity(5)
                .setCart(testCart) // Link to the test cart
                .build();

        CartItem createdCartItem = cartItemService.create(cartItem);

        System.out.println("created test Method");
        System.out.println(createdCartItem);
        System.out.println("-----------------------------");
        assertNotNull(createdCartItem);
        assertNotNull(createdCartItem.getId());
        assertEquals(1L, createdCartItem.getProduct().getId());
    }

    @Test
    @Order(2)
    void read() {
        System.out.println("find By Id/ read");
        CartItem cartItem = new CartItem.Builder()
                .setId(2l)
                .setProduct(product)
                .setQuantity(10)
                .setCart(testCart) // Link to the test cart
                .build();

        CartItem createdCartItem = cartItemService.create(cartItem);
        System.out.println("read method");
        System.out.println(createdCartItem);
        System.out.println("----------------");
        CartItem readCartItem = cartItemService.read(createdCartItem.getId());
        System.out.println("reading the Cart Item");
        System.out.println(readCartItem);
        System.out.println("------------------------");
        assertNotNull(readCartItem);
        assertEquals(createdCartItem.getId(), readCartItem.getId());
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("Update");
        CartItem cartItem = new CartItem.Builder()
                .setId(2l)
                .setProduct(product)
                .setQuantity(15)
                .setCart(testCart) // Link to the test cart
                .build();

        CartItem createdCartItem = cartItemService.create(cartItem);
        System.out.println(createdCartItem);
        CartItem updatedCartItem = new CartItem.Builder()
                .setId(createdCartItem.getId())
                .setProduct(product) // unchanged
                .setQuantity(20)
                .setCart(testCart) // Link to the test cart
                .build();
        System.out.println(updatedCartItem);
        CartItem result = cartItemService.update(updatedCartItem);
        assertNotNull(result);
        //assertEquals(20, result.getQuantity());
        assertEquals(20, result.getQuantity());
    }

    @Test
    @Order(4)
    void findAll() {
        System.out.println("find All");
        CartItem cartItem1 = new CartItem.Builder()
                .setId(null)
                .setProduct(Product)
                .setQuantity(10)
                .setCart(testCart) // Link to the test cart
                .build();

        CartItem cartItem2 = new CartItem.Builder()
                .setId(2l)
                .setProduct(product)
                .setQuantity(5)
                .setCart(testCart) // Link to the test cart
                .build();

        cartItemService.create(cartItem1);
        cartItemService.create(cartItem2);

        List<CartItem> cartItems = cartItemService.findAll();
        System.out.println(cartItems);
        assertNotNull(cartItems);
        assertEquals(2, cartItems.size());
    }

    @Test
    @Order(5)
    void findById() {
        CartItem cartItem = new CartItem.Builder()
                .setId(2l)
                .setProduct(product)
                .setQuantity(8)
                .setCart(testCart) // Link to the test cart
                .build();
        System.out.println("find By Id");
        CartItem createdCartItem = cartItemService.create(cartItem);
        System.out.println(createdCartItem);
        CartItem foundCartItem = cartItemService.read(createdCartItem.getId());
        System.out.println(foundCartItem);
        assertNotNull(foundCartItem);
        assertEquals(createdCartItem.getId(), foundCartItem.getId());
    }

    @Test
    @Order(6)
    void deleteById() {
        System.out.println("Delete By Id");
        CartItem cartItem = new CartItem.Builder()
                .setId(2l)
                .setProduct(product)
                .setQuantity(7)
                .setCart(testCart) // Link to the test cart
                .build();

        CartItem createdCartItem = cartItemService.create(cartItem);
        System.out.println(createdCartItem);
        cartItemService.deleteById(createdCartItem.getId());

        CartItem deletedCartItem = cartItemService.read(createdCartItem.getId());
        assertNull(deletedCartItem);
    }
}
