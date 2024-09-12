package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.CartItem;
import za.ac.cput.service.CartItemService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.GET;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Use a test profile if you have one
class CartItemControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CartItemService cartItemService;

    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        cartItem = new CartItem.Builder()
                .setCartItemID(1L)
                .setCartID(2L)
                .setProductID(3L)
                .setPrice(10.99)
                .build();
        cartItemService.createCartItem(cartItem); // Ensure the cart item exists in the database
    }

    @Test
    void createCartItem_success() {
        CartItem newCartItem = new CartItem.Builder()
                .setCartItemID(2L)
                .setCartID(3L)
                .setProductID(4L)
                .setPrice(20.99)
                .build();

        ResponseEntity<CartItem> response = restTemplate.postForEntity("/cart-items/create",
                newCartItem, CartItem.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getCartID()).isEqualTo(newCartItem.getCartID());
    }

    @Test
    void getCartItemById_success() {
        ResponseEntity<CartItem> response = restTemplate.getForEntity("/cart-items/1", CartItem.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getCartID()).isEqualTo(cartItem.getCartID());
    }

    @Test
    void deleteCartItem_success() {
        restTemplate.delete("/cart-items/delete/1");

        ResponseEntity<CartItem> response = restTemplate.getForEntity("/cart-items/1", CartItem.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
