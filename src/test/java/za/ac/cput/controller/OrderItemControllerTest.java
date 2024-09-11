package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.factory.OrderItemFactory;
import za.ac.cput.service.OrderItemService;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderItemControllerTest {

    @Autowired
    private OrderItemController orderItemController;

    @Autowired
    private OrderItemService orderItemService;

    private OrderItem orderItem;

    @BeforeEach
    void setUp() {
        orderItem = OrderItemFactory.buildOrderItem(
                2L,
                1L,
                10,
                10.00
        );

        // Save the orderItem to generate an orderItemID
        orderItem = orderItemService.create(orderItem);
    }

    @AfterEach
    void tearDown() {
        // Cleanup: Deleting the test orderItem from the database
        if (orderItem != null && orderItem.getOrderItemID() != null) {
            orderItemService.deleteById(orderItem.getOrderItemID());
        }
    }

    @Test
    @Order(1)
    void createOrderItem() {
        // Arrange
        OrderItem newOrderItem = OrderItemFactory.buildOrderItem(
                1L,
                1L,
                5,
                5.00
        );

        // Act
        ResponseEntity<OrderItem> response = orderItemController.createOrderItem(newOrderItem);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newOrderItem.getQuantity(), response.getBody().getQuantity());

        System.out.println(response.getBody());
    }

    @Test
    @Order(2)
    void readOrderItem() {
        // Act
        ResponseEntity<OrderItem> response = orderItemController.getOrderItemById(orderItem.getOrderItemID());

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderItem.getOrderItemID(), response.getBody().getOrderItemID());
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    void updateOrderItem() {
        // Arrange
        OrderItem updatedOrderItem = new OrderItem.Builder()
                .copy(orderItem)
                .setQuantity(15)
                .build();

        // Act
        ResponseEntity<OrderItem> response = orderItemController.updateOrderItem(orderItem.getOrderItemID(), updatedOrderItem);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(15, response.getBody().getQuantity());
        System.out.println(response.getBody());
    }

    @Test
    @Order(4)
    void deleteOrderItem() {
        // Act
        ResponseEntity<Void> response = orderItemController.deleteOrderItem(orderItem.getOrderItemID());

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that the orderItem is deleted by trying to read it
        ResponseEntity<OrderItem> readResponse = orderItemController.getOrderItemById(orderItem.getOrderItemID());
        assertNull(readResponse.getBody());
    }

    @Test
    @Order(5)
    void getAllOrderItems() {
        // Act
        ResponseEntity<List<OrderItem>> response = orderItemController.getAllOrderItems();

        // Assert
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
    }
}
