package za.ac.cput.factory;

import za.ac.cput.domain.Orders;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
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
     * @param orderID     The ID of the Order being placed
     * @param userID      The ID of the customer placing the order.
     * @param addressID   The ID of the address where the order should be delivered.
     * @param totalPrice  The total price of the order.
     * @param status      The status of the order.
     * @param orderDate   The date and time when the order was placed.
     * @param orderItems  The list of order items included in the order.
     * @return            The created Orders object.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    public static Orders buildOrder(
            Long  orderID,
            Long userID,
            Long addressID,
            String status,
            double totalPrice,
            LocalDate orderDate,
            List<OrderItem> orderItems
    ) {

        // Validating input fields
        if (Helper.isNullOrEmpty(userID) ||
            Helper.isNullOrEmpty(addressID) ||
            Helper.isNullOrEmpty(status)) {
            throw new IllegalArgumentException("UserID, AddressID, and Status must not be null or empty.");
        }

        if (Helper.isOrderNullorEmpty(totalPrice)){
            throw new IllegalArgumentException("Total price must be positive and order must contain at least one item.");
        }

        return new Orders.Builder()
                .setOrderID(orderID)
                .setUserID(userID)
                .setAddressID(addressID)
                .setStatus(status)
                .setTotalPrice(totalPrice)
                .setOrderDate(orderDate != null ? orderDate : LocalDate.now())
                .setOrderItems(orderItems)
                .build();
    }
}
