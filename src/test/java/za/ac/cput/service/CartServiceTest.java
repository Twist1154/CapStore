package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.domain.Cart;
import za.ac.cput.repository.ICartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private ICartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    private Cart cart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cart = new Cart.Builder()
                .setCartID(4L)
                .setCustomerID(9L)
                .setCartItems(new ArrayList<>())
                .setTotalAmount(100.00)
                .build();
    }

    @Test
    void create_success() {
        // Mock the repository save method
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        // Call the service method
        Cart createdCart = cartService.create(cart);

        // Assertions
        assertNotNull(createdCart);
        assertEquals(cart.getCartID(), createdCart.getCartID());
        System.out.println(createdCart);
        // Verify that the save method was called
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void read_success() {
        // Mock the repository findById method
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));

        // Call the service method
        Cart foundCart = cartService.read(1L);

        // Assertions
        assertNotNull(foundCart);
        assertEquals(cart.getCartID(), foundCart.getCartID());
        System.out.println(foundCart);
        // Verify that the findById method was called
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    void update_success() {
        // Mock the repository existsById and save methods
        when(cartRepository.existsById(cart.getCartID())).thenReturn(true);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        // Call the service method
        Cart updatedCart = cartService.update(cart);

        // Assertions
        assertNotNull(updatedCart);
        assertEquals(cart.getCartID(), updatedCart.getCartID());
        System.out.println(updatedCart);
        // Verify that existsById and save were called
        verify(cartRepository, times(1)).existsById(cart.getCartID());
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void delete_success() {
        // Call the delete method
        cartService.delete(cart.getCartID());

        // Verify that deleteById was called
        verify(cartRepository, times(1)).deleteById(cart.getCartID());
    }

    @Test
    void findAll_success() {
        // Mock the repository findAll method
        List<Cart> carts = new ArrayList<>();
        carts.add(cart);
        when(cartRepository.findAll()).thenReturn(carts);

        // Call the service method
        List<Cart> foundCarts = cartService.findAll();

        // Assertions
        assertNotNull(foundCarts);
        assertEquals(1, foundCarts.size());

        // Verify that findAll was called
        verify(cartRepository, times(1)).findAll();
    }
}
