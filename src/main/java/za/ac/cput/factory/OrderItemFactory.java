package za.ac.cput.factory;

import za.ac.cput.domain.OrderItem;
import za.ac.cput.util.Helper;

/**
 * OrderItemFactory.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 07-Sep-24
 */

public class OrderItemFactory {

    /**
     * Creates an OrderItem object.
     *
     * @param orderItemId The ID of the order item.
     * @param productID   The ID of the product associated with this order item.
     * @param quantity    The quantity of the product ordered.
     * @param price       The price of the product.
     * @return            The created OrderItem object, or null if any parameter is invalid.
     */
    public static OrderItem buildOrderItem(
            Long orderItemId,
            Long productID,
            int quantity,
            double price) {

        if ( Helper.isNullOrEmpty(productID)) {
            throw new IllegalArgumentException("orderItemId or productID must not be null");
        }

        if (quantity <= 0 || price <= 0) {
            throw new IllegalArgumentException("Price or quantity must not be null");
        }

        return new OrderItem.Builder()
                .setOrderItemId(orderItemId)
                .setProductID(productID)
                .setQuantity(quantity)
                .setPrice(price)
                .build();
    }
}
