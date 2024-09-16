package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CartItem;
import za.ac.cput.service.ICartItemService;

import java.util.List;
import java.util.Optional;

/**
 * CartItemController.java
 *
 * Author: Kinzonzi Mukoko
 * Student Num: 221477934
 * @date 09-Sep-24
 */

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final ICartItemService cartItemService;

    @Autowired
    public CartItemController(ICartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        CartItem createdCartItem = cartItemService.create(cartItem);
        if (createdCartItem != null) {
            return new ResponseEntity<>(createdCartItem, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long id) {
        Optional<CartItem> cartItem = Optional.ofNullable(cartItemService.read(id));
        return cartItem.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, @RequestBody CartItem cartItem) {
        if (!id.equals(cartItem.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CartItem updatedCartItem = cartItemService.update(cartItem);
        return updatedCartItem != null ? ResponseEntity.ok(updatedCartItem) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.findAll();
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        if (cartItemService.read(id) != null) {
            cartItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
