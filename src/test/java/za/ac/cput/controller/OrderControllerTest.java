package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Orders;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderItemFactory;
import za.ac.cput.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderControllerTest.java
 *
 * Test class for OrderController.
 *
 * Author: Rethabile Ntsekhe
 * Student Num: 220455430
 * Date: 07-Sep-24
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @Autowired
    private OrderService orderService;

    private Orders order;

    @BeforeEach
    void setUp() {
        // Create an initial order without items
        order = OrderFactory.buildOrder(
                1L,  // ID should be null for auto-generation
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

    @AfterEach
    void tearDown() {
        // Cleanup: Deleting the test order from the database
        if (order != null && order.getOrderID() != null) {
            orderService.deleteByOrderID(order.getOrderID());
        }
    }

    @Test
    @Order(1)
    void createOrder() {

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

        // Arrange
        Orders newOrder = OrderFactory.buildOrder(
                12L,
                124L,
                1L,
                "pending",
                234,
                LocalDateTime.now(),
                orderItems
        );

        // Act
        ResponseEntity<Orders> response = orderController.createOrder(newOrder);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newOrder.getTotalPrice(), response.getBody().getTotalPrice());

        // Cleanup
        orderService.deleteByOrderID(response.getBody().getOrderID());
    }

    @Test
    @Order(2)
    void read() {
        // Act
        ResponseEntity<Orders> response = orderController.read(order.getOrderID());

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order.getOrderID(), response.getBody().getOrderID());
    }

    @Test
    @Order(3)
    void updateOrder() {
        // Arrange

        // Act
        ResponseEntity<Orders> response = orderController.updateOrder(order.getOrderID(), order);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(150.0, response.getBody().getTotalPrice());
    }

    @Test
    @Order(4)
    void deleteOrder() {
        // Act
        ResponseEntity<Void> response = orderController.deleteOrder(order.getOrderID());

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that the order is deleted by trying to read it
        ResponseEntity<Orders> readResponse = orderController.read(order.getOrderID());
        assertNull(readResponse.getBody());
    }

    @Test
    @Order(5)
    void getAllOrders() {
        // Act
        ResponseEntity<List<Orders>> response = orderController.getAllOrders();

        // Assert
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(6)
    void getOrdersByUserID() {
        // Act
        ResponseEntity<List<Orders>> response = orderController.getOrdersByUserID(order.getUserID());

        // Assert
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(7)
    void getOrdersByStatus() {
        // Act
        ResponseEntity<List<Orders>> response = orderController.getOrdersByStatus("Pending");

        // Assert
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(8)
    void getOrdersByDateRange() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().minusDays(10);
        LocalDateTime endDate = LocalDateTime.now();

        // Act
        ResponseEntity<List<Orders>> response = orderController.getOrdersByDateRange(startDate, endDate);

        // Assert
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(9)
    void getOrdersByAddressID() {
        // Act
        ResponseEntity<List<Orders>> response = orderController.getOrdersByAddressID(order.getAddressID());

        // Assert
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(10)
    void getOrdersByTotalPriceGreaterThan() {
        // Act
        ResponseEntity<List<Orders>> response = orderController.getOrdersByTotalPriceGreaterThan(50.0);
        System.out.println(response.getBody());
        // Assert
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
