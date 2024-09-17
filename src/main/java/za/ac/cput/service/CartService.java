package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Cart;
import za.ac.cput.repository.ICartItemRepository;
import za.ac.cput.repository.ICartRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
        // Add cart items and calculate total price
        double totalPrice = 0;
        for (CartItem item : cart.getCartItems()) {
            cart.addCartItem(item);  // Assuming each CartItem represents one product with its price
            totalPrice += item.getPrice(); // No quantity involved
        }

        // Set the total price
        cart = new Cart.Builder()
                .copy(cart)
                .setTotalPrice(totalPrice)
                .build();

        // Save the cart, which cascades to saving cart items as well
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
                    .setUserID(cart.getUserID())
                    .setCartDate(cart.getCartDate())
                    .setTotalPrice(cart.getTotalPrice())
                    .setCartItems(cart.getCartItems())
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
    public List<Cart> findByUserID(Long userID) {
        return repository.findByUserID(userID);
    }

    @Override
    public List<Cart> findByStatus(String status) {
        return null;
    }

    @Override
    public List<Cart> findByCartDateBetween(LocalDate startDate, LocalDate endDate) {
        return repository.findByCartDateBetween(startDate, endDate);
    }

    @Override
    public List<Cart> findByAddressID(Long addressID) {
        return null;
    }

    @Override
    public List<Cart> findByTotalPriceGreaterThan(double totalPrice) {
        return repository.findByTotalPriceGreaterThan(totalPrice);
    }

    @Override
    public void deleteByCartID(Long cartID) {
        if (repository.existsById(cartID)) {
            repository.deleteById(cartID);
        } else {
            logger.warning("Attempt to delete non-existent cart with ID: " + cartID);
        }
    }

    // Adding an item to the cart
    public Cart addCartItem(Long cartId, CartItem cartItem) {
        Optional<Cart> optionalCart = repository.findById(cartId);

        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();

            cartItem = new CartItem.Builder()
                    .copy(cartItem)
                    .setProductID(cartItem.getProductID())
                    .setCart(cartItem.getCart())
                    .setPrice(cartItem.getPrice())
                    .build();

            cart.addCartItem(cartItem);

            double updatedTotalPrice = 0;
            for (CartItem item : cart.getCartItems()) {
                updatedTotalPrice += item.getPrice();
            }

            Cart updatedCart = new Cart.Builder()
                    .copy(cart)
                    .setTotalPrice(updatedTotalPrice)
                    .build();
            iCartItemRepository.save(cartItem);
            return repository.save(updatedCart);
        } else {
            logger.warning("Attempt to add item to non-existent cart with ID: " + cartId);
            return null;
        }
    }

    // Removing an item from the cart
    public Cart removeCartItem(Long cartId, CartItem cartItem) {
        Optional<Cart> optionalCart = repository.findById(cartId);

        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();

            cart.removeCartItem(cartItem);

            double updatedTotalPrice = 0;
            for (CartItem item : cart.getCartItems()) {
                updatedTotalPrice += item.getPrice();
            }

            Cart updatedCart = new Cart.Builder()
                    .copy(cart)
                    .setTotalPrice(updatedTotalPrice)
                    .build();
            iCartItemRepository.deleteById(cartItem.getId());
            return repository.save(updatedCart);
        } else {
            logger.warning("Attempt to remove item from non-existent cart with ID: " + cartId);
            return null;
        }
    }
}
