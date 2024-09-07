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
        if (cartRepository.existsById(cart.getCartID())) {
            return cartRepository.save(cart);
        }
        return null;
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
