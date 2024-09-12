package za.ac.cput.factory;

import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Orders;
import za.ac.cput.util.Helper;

/**
 * OrderItemFactory.java
 * Factory class to create OrderItem objects.
 *
 * Author: Rethabile Ntsekhe
 * Student Num: 220455430
 * Date: 07-Sep-24
 */

public class OrderItemFactory {

    /**
     * Creates an OrderItem object.
     *
     * @param productID  The ID of the product associated with this order item.
     * @param quantity   The quantity of the product ordered.
     * @param price      The price of the product.
     * @param order      The order of the product.
     * @return           The created OrderItem object.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    public static OrderItem buildOrderItem(
            Long id,
            Long productID,
            int quantity,
            double price,
            Orders order
    ) {

        // Validating input fields
        if (Helper.isNullOrEmpty(productID)) {
            throw new IllegalArgumentException("Product ID must not be null or empty.");
        }

        if (quantity <= 0 || price <= 0) {
            throw new IllegalArgumentException("Quantity and price must be greater than zero.");
        }

        return new OrderItem.Builder()
                .setId(id)
                .setProductID(productID)
                .setQuantity(quantity)
                .setPrice(price)
                .setOrder(order)
                .build();
    }
}
