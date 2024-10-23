package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Orders;
import za.ac.cput.domain.Users;
import za.ac.cput.util.Helper;

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
     * @param id     The ID of the Order being placed
     * @param users      The ID of the customer placing the order.
     * @param address   The ID of the address where the order should be delivered.
     * @param totalPrice  The total price of the order.
     * @param status      The status of the order.
     * @param orderItems  The list of order items included in the order.
     * @return            The created Orders object.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    public static Orders buildOrder(
            Long id,
            Users users,
            Address address,
            String status,
            double totalPrice,
            List<OrderItem> orderItems
    ) {

        // Validating input fields
        if (Helper.isNullOrEmpty(users) ||
            Helper.isNullOrEmpty(address) ||
            Helper.isNullOrEmpty(status)) {
            throw new IllegalArgumentException("UserID, AddressID, and Status must not be null or empty.");
        }

        if (Helper.isOrderNullorEmpty(totalPrice)){
            throw new IllegalArgumentException("Total price must be positive and order must contain at least one item.");
        }

        return new Orders.Builder()
                .setId(id)
                .setUsers(users)
                .setAddress(address)
                .setStatus(status)
                .setTotalPrice(totalPrice)
                .setOrderItems(orderItems)
                .build();
    }
}
