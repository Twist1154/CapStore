package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;

import java.util.List;

public class CartFactory {

    public static Cart createCart(Long cartID,Long customerID, List<CartItem> cartItems, double totalAmount) {
        if (cartID <= 0 || customerID <= 0 || totalAmount <= 0) {
            throw new IllegalArgumentException("Invalid input provided");
        }

        return new Cart.Builder()
                .setCartID(cartID)
                .setCustomerID(customerID)
                .setCartItems(cartItems)
                .setTotalAmount(totalAmount)
                .build();
    }
}
