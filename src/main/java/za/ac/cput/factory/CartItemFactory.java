package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Product;

/**
 * Factory class for creating instances of {@link CartItem}.
 * Provides static methods to create {@link CartItem} objects from various inputs.
 *
 * @author Rethabile Ntsekhe
 * @date 24-Aug-24
 */
public class CartItemFactory {

    /**
     * Creates a {@link CartItem} instance from various inputs.
     *
     * @param id       the unique identifier for the cart item
     * @param cart     the {@link Cart} entity associated with this cart item
     * @param product  the {@link Product} entity associated with this cart item
     * @param quantity the quantity of the cart item
     * @return a new {@link CartItem} object with properties set from the input parameters
     * @throws IllegalArgumentException if any of the input parameters are invalid
     */
    public static CartItem createCartItem(Long id,
                                          Cart cart,
                                          Product product,
                                          int quantity) {
        // Define constants for the error flags
        final int CART_NULL = 1;
        final int PRODUCT_NULL = 2;
        final int QUANTITY_INVALID = 4;

        // Calculate the errorFlags based on null checks and invalid quantity
        int errorFlags = 0;

        if (cart == null) {
            errorFlags |= CART_NULL;
        }
        if (product == null) {
            errorFlags |= PRODUCT_NULL;
        }
        if (quantity <= 0) {
            errorFlags |= QUANTITY_INVALID;
        }

        // Use switch statement to throw exception based on the flags
        switch (errorFlags) {
            case CART_NULL | PRODUCT_NULL | QUANTITY_INVALID:
                throw new IllegalArgumentException("Cart, Product cannot be null and Quantity must be greater than zero");
            case CART_NULL | PRODUCT_NULL:
                throw new IllegalArgumentException("Cart and Product cannot be null");
            case CART_NULL | QUANTITY_INVALID:
                throw new IllegalArgumentException("Cart cannot be null and Quantity must be greater than zero");
            case PRODUCT_NULL | QUANTITY_INVALID:
                throw new IllegalArgumentException("Product cannot be null and Quantity must be greater than zero");
            case CART_NULL:
                throw new IllegalArgumentException("Cart cannot be null");
            case PRODUCT_NULL:
                throw new IllegalArgumentException("Product cannot be null");
            case QUANTITY_INVALID:
                throw new IllegalArgumentException("Quantity must be greater than zero");
            default:
                // No null values or invalid quantity
                break;
        }

        // Use the Builder pattern to create a new CartItem object
        return new CartItem.Builder()
                .setId(id)
                .setCart(cart)        // Set the cart associated with the cart item
                .setProduct(product)  // Set the product associated with the cart item
                .setQuantity(quantity) // Set the quantity of the cart item
                .build();
    }
}
