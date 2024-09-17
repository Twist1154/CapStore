package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Cart;
import za.ac.cput.repository.ICartItemRepository;

import java.time.LocalDate;
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
    private CartService cartService; // Add CartService to link CartItem with Cart

    private static Cart testCart;

    @BeforeEach
    void setUp() {
        // Setup a test cart
        testCart = new Cart.Builder()
                .setId(55L)
                .setUserID(1L)
                .setTotalPrice(100.0)
                .setCartDate(LocalDate.now())
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
                .setProductID(1L)
                //.setQuantity(5)
                .setPrice(100.0)
                .setCart(testCart) // Link to the test cart
                .build();

        CartItem createdCartItem = cartItemService.create(cartItem);

        System.out.println("created test Method");
        System.out.println(createdCartItem);
        System.out.println("-----------------------------");
        assertNotNull(createdCartItem);
        assertNotNull(createdCartItem.getId());
        assertEquals(1L, createdCartItem.getProductID());
    }

    @Test
    @Order(2)
    void read() {
        System.out.println("find By Id/ read");
        CartItem cartItem = new CartItem.Builder()
                .setId(2l)
                .setProductID(2L)
                //.setQuantity(10)
                .setPrice(200.0)
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
                .setProductID(3L)
                //.setQuantity(15)
                .setPrice(300.0)
                .setCart(testCart) // Link to the test cart
                .build();

        CartItem createdCartItem = cartItemService.create(cartItem);
        System.out.println(createdCartItem);
        CartItem updatedCartItem = new CartItem.Builder()
                .setId(createdCartItem.getId())
                .setProductID(3L) // unchanged
                //.setQuantity(20)
                .setPrice(350.0)
                .setCart(testCart) // Link to the test cart
                .build();
        System.out.println(updatedCartItem);
        CartItem result = cartItemService.update(updatedCartItem);
        assertNotNull(result);
        //assertEquals(20, result.getQuantity());
        assertEquals(350.0, result.getPrice());
    }

    @Test
    @Order(4)
    void findAll() {
        System.out.println("find All");
        CartItem cartItem1 = new CartItem.Builder()
                .setId(2l)
                .setProductID(4L)
                //.setQuantity(10)
                .setPrice(400.0)
                .setCart(testCart) // Link to the test cart
                .build();

        CartItem cartItem2 = new CartItem.Builder()
                .setId(2l)
                .setProductID(5L)
                //.setQuantity(5)
                .setPrice(500.0)
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
                .setProductID(7L)
                //.setQuantity(8)
                .setPrice(700.0)
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
                .setProductID(6L)
                //.setQuantity(7)
                .setPrice(600.0)
                .setCart(testCart) // Link to the test cart
                .build();

        CartItem createdCartItem = cartItemService.create(cartItem);
        System.out.println(createdCartItem);
        cartItemService.deleteById(createdCartItem.getId());

        CartItem deletedCartItem = cartItemService.read(createdCartItem.getId());
        assertNull(deletedCartItem);
    }
}
