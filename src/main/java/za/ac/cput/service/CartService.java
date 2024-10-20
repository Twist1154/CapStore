package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Cart;
import za.ac.cput.repository.ICartItemRepository;
import za.ac.cput.repository.ICartRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

/**
 * CartService.java
 *
 * Service class to manage Carts.
 *
 * Author: Kinzonzi Mukoko
 * Student Num: 221477934
 * Date: 07-Sep-24
 */
@Service
@Transactional
public class CartService implements ICartService {
    private final ICartRepository repository;
    private final ICartItemRepository iCartItemRepository;
    private static final Logger logger = Logger.getLogger(CartService.class.getName());

    @Autowired
    public CartService(ICartRepository repository, ICartItemRepository iCartItemRepository) {
        this.repository = repository;
        this.iCartItemRepository = iCartItemRepository;
    }

    @Override
    public Cart create(Cart cart) {
        return repository.save(cart);
    }

    @Override
    public Cart read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Cart update(Cart cart) {
        return repository.findById(cart.getId()).map(existingCart -> {
            Cart updatedCart = new Cart.Builder()
                    .copy(existingCart)
                    .setUser(cart.getUser())
                    .setTotal(cart.getTotal())
                    .setDiscount(cart.getDiscount())
                    .build();
            return repository.save(updatedCart);
        }).orElseGet(() -> {
            logger.warning("Attempt to update non-existing cart with ID: " + cart.getId());
            return null;
        });
    }

    @Override
    public List<Cart> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Cart> findByUser_Id(Long id) {
        return repository.findByUser_Id(id);
    }

    @Override
    public List<Cart> findByStatus(String status) {
        return null;
    }

    @Override
    public List<Cart> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByCreatedAtBetween(startDate, endDate);
    }


    @Override
    public List<Cart> findByTotalGreaterThan(double totalPrice) {
        return repository.findByTotalGreaterThan(totalPrice);
    }

    @Override
    public void deleteByCartID(Long cartID) {
        if (repository.existsById(cartID)) {
            repository.deleteById(cartID);
        } else {
            logger.warning("Attempt to delete non-existent cart with ID: " + cartID);
        }
    }
}
