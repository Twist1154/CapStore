package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.Cart;
import za.ac.cput.service.CartService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CartControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CartService cartService;

    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart.Builder()
                .setCartID(1L)
                .setCustomerID(1L)
                .setCartItems(new ArrayList<>())
                .setTotalAmount(100.00)
                .build();
    }

    @Test
    void createCart_success() {
        // Create a new Cart via the API call
        ResponseEntity<Cart> response = restTemplate.postForEntity("/cart/create",
                new Cart.Builder()
                        .setCustomerID(1L)
                        .setCartItems(new ArrayList<>()) // empty cart items
                        .setTotalAmount(100.00)
                        .build(),
                Cart.class);

        // Assert that the response status is 200 OK
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Check if the response body is not null
        assertThat(response.getBody()).isNotNull();

        // Get the Cart from the response body
        Cart createdCart = response.getBody();

        // Assert that the Cart ID is greater than 0 (assuming auto-generated IDs)
        assertThat(createdCart.getCartID()).isGreaterThan(0L);
    }



    @Test
    void readCart_success() {
        // Make a POST request to create the cart
        ResponseEntity<Cart> response = restTemplate.postForEntity("/cart/create",
                new Cart.Builder()
                        .setCustomerID(1L)
                        .setCartItems(new ArrayList<>()) // empty cart items
                        .setTotalAmount(100.00)
                        .build(),
                Cart.class);

        // Check that the response status is OK
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Ensure the response body is not null
        assertThat(response.getBody()).isNotNull();

        // Get the cart object from the response body
        Cart createdCart = response.getBody();

        // Ensure the cart has a valid ID (non-null or greater than 0)
        assertThat(createdCart.getCartID()).isGreaterThan(0L);
    }


    @Test
    void updateCart_success() {
        // Step 1: Create a new cart (ensure it exists)
        ResponseEntity<Cart> createResponse = restTemplate.postForEntity("/cart/create",
                new Cart.Builder()
                        .setCustomerID(1L)
                        .setCartItems(new ArrayList<>()) // Initial cart items (empty)
                        .setTotalAmount(100.00)
                        .build(),
                Cart.class);

        // Assert that the cart was created successfully
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Cart createdCart = createResponse.getBody();
        assertThat(createdCart).isNotNull(); // Ensure the cart is not null
        Long cartID = createdCart.getCartID();
        assertThat(cartID).isNotNull();  // Ensure the cart ID is valid

        // Step 2: Prepare the updated cart data
        Cart updatedCart = new Cart.Builder()
                .setCartID(cartID)  // Use the cartID from the created cart
                .setCustomerID(1L)
                .setCartItems(new ArrayList<>())  // Updated cart items (empty for now)
                .setTotalAmount(200.00)  // Updated total amount
                .build();

        // Step 3: Perform PUT request to update the cart
        ResponseEntity<Void> putResponse = restTemplate.exchange(
                "/cart/update/{cartID}", HttpMethod.PUT, new HttpEntity<>(updatedCart), Void.class, cartID);

        // Assert that the PUT request was successful
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Step 4: Perform GET request to verify the updated cart
        ResponseEntity<Cart> updatedResponse = restTemplate.getForEntity("/read/{cartID}", Cart.class, cartID);
        Cart updatedResponseBody = updatedResponse.getBody();

        // Assert the response status and updated fields
        assertThat(updatedResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assert updatedResponseBody != null;
        assertThat(updatedResponseBody.getTotalAmount()).isEqualTo(200.00);
    }





    @Test
    void deleteCart_success() {
        // First, create a new cart
        ResponseEntity<Cart> response = restTemplate.postForEntity("/cart/create",
                new Cart.Builder()
                        .setCustomerID(1L)
                        .setCartItems(new ArrayList<>()) // empty cart items
                        .setTotalAmount(100.00)
                        .build(),
                Cart.class);

        // Assert that the cart was created successfully
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Cart createdCart = response.getBody();
        assertThat(createdCart).isNotNull();

        // Then, delete the created cart
        Long cartId = createdCart.getCartID();
        restTemplate.delete("/cart/delete/" + cartId);

        // Try to retrieve the deleted cart (should return 404)
        ResponseEntity<Void> getResponse = restTemplate.getForEntity("/cart/" + cartId, Void.class);

        // Assert that retrieving the deleted cart returns a 404 Not Found
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    void getAllCarts_success() {
        try {
            ResponseEntity<List<Cart>> response = restTemplate.exchange(
                    "/cart/all", HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Cart>>() {}
            );

            // Debugging output
            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().size()).isGreaterThan(0);  // Check there are carts returned
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
