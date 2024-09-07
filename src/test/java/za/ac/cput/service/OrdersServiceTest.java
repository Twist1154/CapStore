package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Orders;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderItemFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrdersServiceTest.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 07-Sep-24
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrdersServiceTest {

    @Autowired
    private OrderService orderService;

    private Orders order;

    @BeforeEach
    void setUp() {
        // Create an initial order without items
        order = OrderFactory.buildOrder(
                null,  // ID should be null for auto-generation
                1L,  // userID
                1L,  // addressID
                "Pending",  // status
                150.0,  // totalPrice
                LocalDateTime.now(),  // orderDate
                new ArrayList<>()  // Empty list of orderItems initially
        );

        // Save the order to generate an orderID
        order = orderService.create(order);

        // Create test OrderItems for the saved order
        OrderItem orderItem1 = OrderItemFactory.buildOrderItem(
                1L,  // null for auto-generated orderItemID
                12,  // quantity
                12.00  // price
        );

        OrderItem orderItem2 = OrderItemFactory.buildOrderItem(
                1L,
                5,
                10.00
        );

        OrderItem orderItem3 = OrderItemFactory.buildOrderItem(
                1L,
                20,
                20.00
        );

        // Add OrderItems to a list
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderItems.add(orderItem3);

        // Update the order with OrderItems and save it
        order = new Orders.Builder()
                .setOrderID(order.getOrderID())
                .setUserID(order.getUserID())
                .setAddressID(order.getAddressID())
                .setTotalPrice(order.getTotalPrice())
                .setStatus(order.getStatus())
                .setOrderDate(order.getOrderDate())
                .setOrderItems(orderItems)  // Set orderItems here
                .build();

        orderService.update(order);
    }

    @Test
    void create() {
        Orders createdOrder = orderService.create(order);
        assertNotNull(createdOrder);
        assertEquals(order.getOrderID(), createdOrder.getOrderID());
    }

    @Test
    void read() {
        Orders readOrder = orderService.read(order.getOrderID());
        assertNotNull(readOrder);
        assertEquals(order.getOrderID(), readOrder.getOrderID());
    }

    @Test
    void update() {
        // Update the order details
        Orders updatedOrder = new Orders.Builder()
                .setOrderID(order.getOrderID())
                .setUserID(1L) // Updated userID
                .setAddressID(order.getAddressID())
                .setOrderDate(order.getOrderDate())
                .setOrderItems(order.getOrderItems())
                .setTotalPrice(200.0) // Updated totalPrice
                .setStatus("Shipped") // Updated status
                .build();

        Orders result = orderService.update(updatedOrder);
        assertNotNull(result);
        assertEquals(updatedOrder.getTotalPrice(), result.getTotalPrice());
        assertEquals(updatedOrder.getStatus(), result.getStatus());
    }

    @Test
    void getAll() {
        List<Orders> orders = orderService.findAll();
        assertFalse(orders.isEmpty());
    }

    @Test
    void delete() {
        Long orderIdToDelete = order.getOrderID();
        orderService.deleteByOrderID(orderIdToDelete);

        Orders deletedOrder = orderService.read(orderIdToDelete);
        assertNull(deletedOrder);
    }
}
