package za.ac.cput.factory;

import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Cart;
import za.ac.cput.util.Helper;

/**
 * CartItemFactory.java
 * Factory class to create CartItem objects.
 *
 * Author: Kinzonzi Mukoko
 * Student Num: 221477934
 * Date: 07-Sep-24
 */

public class CartItemFactory {

    /**
     * Creates a CartItem object.
     *
     * @param productID  The ID of the product associated with this cart item.
     //* @param quantity   The quantity of the product ordered.
     * @param price      The price of the product.
     * @param cart       The cart of the product.
     * @return           The created CartItem object.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    public static CartItem buildCartItem(
            Long id,
            Long productID,
            double price,
            Cart cart
    ) {

        // Validating input fields
        if (Helper.isNullOrEmpty(productID)) {
            throw new IllegalArgumentException("Product ID must not be null or empty.");
        }

        if ( price <= 0) {
            throw new IllegalArgumentException("Quantity and price must be greater than zero.");
        }

        return new CartItem.Builder()
                .setId(id)
                .setProductID(productID)
                .setPrice(price)
                .setCart(cart)
                .build();
    }
}
