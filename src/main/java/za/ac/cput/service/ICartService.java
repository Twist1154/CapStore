package za.ac.cput.service;

import za.ac.cput.domain.Cart;

import java.util.List;

public interface ICartService extends IService<Cart, Long>{
    void delete(Long id);

    Cart update(Long cartID, Cart cart);

    List<Cart> findAll();
}
