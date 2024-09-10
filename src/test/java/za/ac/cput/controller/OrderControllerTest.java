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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderControllerTest.java
 *
 * Test class for OrderController.
 *
 * Author: [Your Name]
 * Date: [Current Date]
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
                1L,
                1L,
                "Pending",
                150.0,
                LocalDate.now(),
                new ArrayList<>()
        );

        // Save the order to generate an orderID
        order = orderService.create(order);

        // Create test OrderItems for the saved order
        OrderItem orderItem1 = OrderItemFactory.buildOrderItem(
                1L,
                1L,
                12,
                12.00
        );

        OrderItem orderItem2 = OrderItemFactory.buildOrderItem(
                4L,
                1L,
                5,
                10.00
        );

        OrderItem orderItem3 = OrderItemFactory.buildOrderItem(
                null,
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
        // Arrange
        OrderItem orderItem1 = OrderItemFactory.buildOrderItem(
                null,
                1L,  // null for auto-generated orderItemID
                12,
                12.00
        );

        OrderItem orderItem2 = OrderItemFactory.buildOrderItem(
                null,
                1L,
                5,
                10.00
        );

        OrderItem orderItem3 = OrderItemFactory.buildOrderItem(
                null,
                1L,
                20,
                20.00
        );

        // Add OrderItems to a list
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderItems.add(orderItem3);

        Orders newOrder = OrderFactory.buildOrder(
                12L,
                124L,
                1L,
                "pending",
                234,
                LocalDate.now(),
                orderItems
        );

        // Act
        ResponseEntity<Orders> response = orderController.createOrder(newOrder);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newOrder.getTotalPrice(), response.getBody().getTotalPrice());
        System.out.println(response.getBody());

        // Cleanup
        //orderService.deleteByOrderID(response.getBody().getOrderID());
    }

    @Test
    @Order(2)
    void read() {
        // Act
        ResponseEntity<Orders> response = orderController.read(order.getOrderID());
        System.out.println(response.getBody());

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order.getOrderID(), response.getBody().getOrderID());
    }

    @Test
    @Order(3)
    void updateOrder() {
        // Arrange
        Orders updatedOrder = new Orders.Builder()
                .copy(order)
                .setTotalPrice(200.0)
                .build();

        // Act
        ResponseEntity<Orders> response = orderController.updateOrder(order.getOrderID(), updatedOrder);
        System.out.println(response.getBody());
        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200.0, response.getBody().getTotalPrice());
    }

    @Test
    @Order(4)
    void deleteOrder() {
        System.out.println((order.getOrderID()));
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
        System.out.println(response.getBody());

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

        System.out.println(response.getBody());

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
        System.out.println(response.getBody());
        // Assert
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(8)
    void getOrdersByDateRange() {
        // Arrange
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();
        // Act
        ResponseEntity<List<Orders>> response = orderController.getOrdersByDateRange(startDate, endDate);
        System.out.println(response.getBody());
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
        System.out.println(response.getBody());

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
