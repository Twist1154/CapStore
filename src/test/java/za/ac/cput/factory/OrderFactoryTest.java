package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Orders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderFactoryTest {

    @Test
    public void testCreateOrder() {
        // Create test OrderItems with valid data
        OrderItem orderItem1 = OrderItemFactory.buildOrderItem(
                1L,
                12L,
                2,
                12.00,
                null
        );

        OrderItem orderItem2 = OrderItemFactory.buildOrderItem(
                2L,
                5L,
                1,
                10.00,
                null
        );

        OrderItem orderItem3 = OrderItemFactory.buildOrderItem(
                null,
                20L,
                3,
                20.00,
                null
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
        LocalDate orderDate = LocalDate.now();

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
        assertEquals(orderID, order.getId());
        assertEquals(userID, order.getUserID());
        assertEquals(addressID, order.getAddressID());
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
        LocalDate orderDate = LocalDate.now();

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
        LocalDate orderDate = LocalDate.now();

        // Add empty order items for this test
        List<OrderItem> orderItems = new ArrayList<>();

        // Expecting IllegalArgumentException due to negative total price
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            OrderFactory.buildOrder(
                    orderID,
                    userID,
                    addressID,
                    status,
                    totalPrice,
                    orderDate,
                    orderItems
            );
        });

        assertEquals("Total price must be positive and order must contain at least one item.", thrown.getMessage());
    }
}
