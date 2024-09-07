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
                1L,
                12,
                12.00
        );

        OrderItem orderItem2 = OrderItemFactory.buildOrderItem(
                2L,
                5,
                10.00
        );

        OrderItem orderItem3 = OrderItemFactory.buildOrderItem(
                3L,
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

        // Additional assertions to ensure the order details are correct
        assertEquals(orderID, order.getOrderID());
        assertEquals(userID, order.getUserID());
        assertEquals(orderDate, order.getOrderDate());
        assertEquals(orderItems, order.getOrderItems());
        assertEquals(totalPrice, order.getTotalPrice());
        assertEquals(status, order.getStatus());

        // Print the created order for debugging
        System.out.println(order);
    }

    @Test
    public void testCreateOrderWithEmptyItems() {
        // Creating test data for the Orders object with no order items
        Long orderID = 2L;
        Long userID = 1L;
        Long addressID = 1L;
        double totalPrice = 0.0; // Assuming 0 for no items
        String status = "Pending";
        LocalDateTime orderDate = LocalDateTime.now();

        // Build the Orders object with an empty orderItems list
        List<OrderItem> emptyOrderItems = new ArrayList<>();
        Orders order = OrderFactory.buildOrder(
                orderID,
                userID,
                addressID,
                status,
                totalPrice,
                orderDate,
                emptyOrderItems
        );

        // Asserting that the created order is not null but has no items
        assertNotNull(order);
        assertTrue(order.getOrderItems().isEmpty());
        assertEquals(0.0, order.getTotalPrice());
    }

    @Test
    public void testCreateOrderWithNegativeTotalPrice() {
        // Creating test data for the Orders object with a negative total price
        Long orderID = 3L;
        Long userID = 1L;
        Long addressID = 1L;
        double totalPrice = -50.0;
        String status = "Pending";
        LocalDateTime orderDate = LocalDateTime.now();

        // Add empty order items for this test
        List<OrderItem> orderItems = new ArrayList<>();

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

        // Asserting that the order allows negative total price (this may vary depending on validation logic)
        assertNotNull(order);
        assertEquals(-50.0, order.getTotalPrice());
    }
}
