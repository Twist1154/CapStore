package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Cart;
import za.ac.cput.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CartController.java
 *
 * Controller class to handle HTTP requests for Carts.
 *
 * Author: Kinzonzi Mukoko
 * Student Num: 221477934
 * Date: 07-Sep-24
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Create a new cart
    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        try {
            if (cart == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Cart newCart = cartService.create(cart);
            return new ResponseEntity<>(newCart, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating cart", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read a cart by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Cart> read(@PathVariable Long id) {
        try {
            Cart cart = cartService.read(id);
            if (cart != null) {
                return new ResponseEntity<>(cart, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error reading cart with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing cart
    @PutMapping("/update/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        try {
            Cart existingCart = cartService.read(id);
            if (existingCart == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Cart updatedCart = cartService.update(cart);
            return updatedCart != null ? ResponseEntity.ok(updatedCart) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error updating cart with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a cart by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        try {
            Cart existingCart = cartService.read(id);
            if (existingCart == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            cartService.deleteByCartID(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting cart with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all carts
    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getAllCarts() {
        try {
            List<Cart> cartList = cartService.findAll();
            if (cartList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(cartList);
        } catch (Exception e) {
            logger.error("Error fetching all carts", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get carts by userID
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<Cart>> getCartsByUserID(@PathVariable Long userID) {
        try {
            List<Cart> cartList = cartService.findByUser_Id(userID);
            if (cartList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(cartList);
        } catch (Exception e) {
            logger.error("Error fetching carts for userID " + userID, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get carts by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Cart>> getCartsByStatus(@PathVariable String status) {
        try {
            List<Cart> cartList = cartService.findByStatus(status);
            if (cartList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(cartList);
        } catch (Exception e) {
            logger.error("Error fetching carts with status " + status, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get carts by date range
    @GetMapping("/date-range")
    public ResponseEntity<List<Cart>> getCartsByDateRange(@RequestParam LocalDateTime startDate,
                                                          @RequestParam LocalDateTime endDate) {
        try {
            if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<Cart> cartList = cartService.findByCreatedAtBetween(startDate, endDate);
            if (cartList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(cartList);
        } catch (Exception e) {
            logger.error("Error fetching carts between dates " + startDate + " and " + endDate, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Get carts with a total price greater than a specified value
    @GetMapping("/total-price/{totalPrice}")
    public ResponseEntity<List<Cart>> getCartsByTotalPriceGreaterThan(@PathVariable double totalPrice) {
        logger.info("Fetching carts with totalPrice greater than: " + totalPrice);
        try {
            if (totalPrice < 0) {
                logger.warn("Invalid totalPrice value: " + totalPrice);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            List<Cart> cartList = cartService.findByTotalGreaterThan(totalPrice);
            logger.info("Fetched " + cartList.size() + " carts.");

            if (cartList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.ok(cartList);
        } catch (Exception e) {
            logger.error("Error fetching carts with totalPrice greater than " + totalPrice, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


