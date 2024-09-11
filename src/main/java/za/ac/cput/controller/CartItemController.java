package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CartItem;
import za.ac.cput.factory.CartItemFactory;
import za.ac.cput.service.CartItemService;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/create")
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        CartItem newCartItem = CartItemFactory.createCartItem(
                cartItem.getCartID(),
                cartItem.getProductID(),
                cartItem.getPrice()
        );
        return ResponseEntity.ok(cartItemService.createCartItem(newCartItem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable long id) {
        return cartItemService.getCartItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable long id, @RequestBody CartItem cartItem) {
        return cartItemService.getCartItemById(id)
                .map(existingCartItem -> {
                    CartItem updatedCartItem = new CartItem.Builder()
                            .setCartItemID(id)
                            .setCartID(cartItem.getCartID())
                            .setProductID(cartItem.getProductID())
                            .setPrice(cartItem.getPrice())
                            .build();
                    return ResponseEntity.ok(cartItemService.updateCartItem(updatedCartItem));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}

