package za.ac.cput.service;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;

import java.util.List;

public interface ICartItemService extends IService<CartItem, Long>{
    void delete(Long id);

    List<CartItem> findAll();
}
