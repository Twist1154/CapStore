package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Cart;
import za.ac.cput.repository.ICartRepository;


import java.util.List;

@Service
public class CartService implements ICartService {

    private ICartRepository cartRepository;

    @Autowired
    CartService(ICartRepository cartRepository){this.cartRepository = cartRepository;}


    @Override
    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart read(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart update(Cart cart) {
        return null;
    }

    @Override
    public Cart update(Long cartID, Cart cart) {
        // Check if cart exists
        if (cartRepository.existsById(cartID)) {
            // Rebuild the cart with the updated cartID
            Cart updatedCart = new Cart.Builder()
                    .setCartID(cartID)
                    .setCustomerID(cart.getCustomerID())
                    .setCartItems(cart.getCartItems())
                    .setTotalAmount(cart.getTotalAmount())
                    .build();

            // Save and return the updated cart
            return cartRepository.save(updatedCart);
        }
        return null;  // Return null if cart does not exist
    }


    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }
}
