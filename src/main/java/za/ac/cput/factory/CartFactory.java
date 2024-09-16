package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;

/**
 * CartFactory.java
 * Factory class to create Cart objects.
 *
 *Author: Kinzonzi Mukoko
 * Student Num: 221477934
 */
public class CartFactory {


    public static Cart buildCart(
            Long id,
            double totalPrice,
            LocalDate cartDate,
            List<CartItem> cartItems
    ) {

        if (Helper.isOrderNullorEmpty(totalPrice)){
            throw new IllegalArgumentException("Total price must be positive and cart must contain at least one item.");
        }

        return new Cart.Builder()
                .setId(id)
                .setTotalPrice(totalPrice)
                .setCartDate(cartDate != null ? cartDate : LocalDate.now())
                .setCartItems(cartItems)
                .build();
    }
}
