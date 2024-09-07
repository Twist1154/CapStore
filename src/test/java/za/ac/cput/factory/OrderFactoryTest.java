package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Orders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderFactoryTest {

    @Test
    public void testCreateOrder() {
        // Create test OrderItems with valid data
        OrderItem orderItem1 = OrderItemFactory.buildOrderItem(
                1L,  // Assuming non-null orderItemId for testing
                1L,
                12,
                12.00
        );

        OrderItem orderItem2 = OrderItemFactory.buildOrderItem(
                2L,  // Assuming non-null orderItemId for testing
                1L,
                5,
                10.00
        );

        OrderItem orderItem3 = OrderItemFactory.buildOrderItem(
                3L,  // Assuming non-null orderItemId for testing
                2L,
                20,
                20.00
        );

        // Ensure that the OrderItems were created successfully and not null
        assertNotNull(orderItem1);
        assertNotNull(orderItem2);
        assertNotNull(orderItem3);

        // Add OrderItems to a list
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderItems.add(orderItem3);

        // Creating test data for the Orders object
        Long orderID = 1L;
        Long userID = 1L;
        Long addressID = 1L;
        double totalPrice = 150.0;
        String status = "Pending";
        LocalDateTime orderDate = LocalDateTime.now();

        // Build the Orders object
        Orders order = OrderFactory.buildOrder(
                orderID,
                userID,
                addressID,
                status,
                totalPrice,
                orderDate,
                orderItems
        );

        // Asserting that the created order is not null
        assertNotNull(order);

        // Print the created order for debugging
        System.out.println(order);

        // Additional assertions to ensure the order details are correct
        assertEquals(orderID, order.getOrderID());
        assertEquals(userID, order.getUserID());
        assertEquals(orderDate, order.getOrderDate());
        assertEquals(orderItems, order.getOrderItems());
        assertEquals(totalPrice, order.getTotalPrice());
        assertEquals(status, order.getStatus());
    }
}
