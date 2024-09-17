package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.CartItem;
import za.ac.cput.repository.ICartItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * CartItemService.java
 *
 * Author: Kinzonzi Mukoko
 * Student Num: 221477934
 * Date: 09-Sep-24
 */

@Transactional
@Service
public class CartItemService implements ICartItemService {
    private final ICartItemRepository repository;
    private static final Logger logger = Logger.getLogger(CartItemService.class.getName());

    @Autowired
    public CartItemService(ICartItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public CartItem create(CartItem cartItem) {
        return repository.save(cartItem);
    }

    @Override
    public CartItem read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CartItem update(CartItem cartItem) {
        CartItem existingCartItem = repository.findById(cartItem.getId()).orElse(null);
        if (existingCartItem != null) {
            CartItem updatedCartItem = new CartItem.Builder()
                    .copy(existingCartItem)
                    .setProductID(cartItem.getProductID())
                    //.setQuantity(cartItem.getQuantity())
                    .setPrice(cartItem.getPrice())
                    .setCart(cartItem.getCart())
                    .build();
            return repository.save(updatedCartItem);
        } else {
            logger.warning("Attempt to update a non-existent cart item with ID: " + cartItem.getId());
            return null;
        }
    }

    @Override
    public List<CartItem> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            logger.warning("Attempt to delete a non-existent cart item with ID: " + id);
        }
    }
}
