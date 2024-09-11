package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Cart;
import za.ac.cput.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public Cart create(@RequestBody Cart cart) {
        return cartService.create(cart);
    }

    @GetMapping("/read/{id}")
    public Cart read(@PathVariable long id) {
        return cartService.read(id);
    }

    @PutMapping("/update/{cartID}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long cartID, @RequestBody Cart cart) {
        Cart updatedCart = cartService.update(cartID, cart);  // Add logic in service to handle cartID
        return updatedCart != null ? ResponseEntity.ok(updatedCart) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        cartService.delete(id);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.findAll();
        return ResponseEntity.ok(carts);
    }


}

