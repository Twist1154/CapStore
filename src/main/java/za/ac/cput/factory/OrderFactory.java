package za.ac.cput.factory;

import za.ac.cput.domain.Orders;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OrderFactory.java
 * Factory class to create Orders objects.
 *
 * Author: Rethabile Ntsekhe (220455430)
 * Date: 07-Sep-24
 */
public class OrderFactory {

    /**
     * Creates an Orders object.
     *
     * @param userID      The ID of the customer placing the order.
     * @param orderDate   The date and time when the order was placed.
     * @param orderItems  The list of order items included in the order.
     * @param totalPrice  The total price of the order.
     * @param status      The status of the order.
     * @param addressID   The ID of the address where the order should be delivered.
     * @return            The created Orders object.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    public static Orders buildOrder(
            Long  orderID,
            Long userID,
            Long addressID,
            String status,
            double totalPrice,
            LocalDateTime orderDate,
            List<OrderItem> orderItems
    ) {

        // Validating input fields
        if (orderDate == null || totalPrice <= 0 || Helper.isNullOrEmpty(status)) {
            throw new IllegalArgumentException("Invalid input: fields must not be null or empty.");
        }

        return new Orders.Builder()
                .setOrderID(orderID)
                .setUserID(userID)
                .setAddressID(addressID)
                .setStatus(status)
                .setTotalPrice(totalPrice)
                .setOrderDate(LocalDateTime.now())
                .setOrderItems(orderItems)
                .build();
    }
}
