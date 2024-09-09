package za.ac.cput.factory;

import za.ac.cput.domain.CartItem;

public class CartItemFactory {
    public static CartItem createCartItem(Long cartID, Long productID, double price) {
        if (cartID <= 0 || productID <= 0 || price <= 0) {
            throw new IllegalArgumentException("Invalid input provided");
        }

        return new CartItem.Builder()
                .setCartID(cartID)
                .setProductID(productID)
                .setPrice(price)
                .build();
    }
}
