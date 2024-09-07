package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.domain.CartItem;
import za.ac.cput.repository.ICartItemRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartItemServiceTest {

    @Mock
    private ICartItemRepository cartItemRepository;

    @InjectMocks
    private CartItemService cartItemService;

    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartItem = new CartItem.Builder()
                .setCartItemID(1L)
                .setCartID(2L)
                .setProductID(3L)
                .setPrice(10.99)
                .build();
    }

    @Test
    void createCartItem_success() {
        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);

        CartItem savedCartItem = cartItemService.createCartItem(cartItem);
        assertNotNull(savedCartItem);
        assertEquals(cartItem.getCartID(), savedCartItem.getCartID());
        verify(cartItemRepository, times(1)).save(cartItem);
        System.out.println(savedCartItem);
    }

    @Test
    void getCartItemById_success() {
        when(cartItemRepository.findById(1L)).thenReturn(Optional.of(cartItem));

        Optional<CartItem> foundCartItem = cartItemService.getCartItemById(1L);
        assertTrue(foundCartItem.isPresent());
        assertEquals(cartItem.getCartID(), foundCartItem.get().getCartID());
        verify(cartItemRepository, times(1)).findById(1L);
        System.out.println(foundCartItem);
    }

    @Test
    void deleteCartItem_success() {
        doNothing().when(cartItemRepository).deleteById(1L);

        cartItemService.deleteCartItem(1L);
        verify(cartItemRepository, times(1)).deleteById(1L);
    }
}
