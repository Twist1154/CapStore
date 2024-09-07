package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CartItem;
import za.ac.cput.repository.ICartItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final ICartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(ICartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public Optional<CartItem> getCartItemById(long id) {
        return cartItemRepository.findById(id);
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem updateCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(long id) {
        cartItemRepository.deleteById(id);
    }
}

