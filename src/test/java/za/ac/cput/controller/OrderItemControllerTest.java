package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.OnlineClothingStoreApp;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Orders;
import za.ac.cput.factory.OrderItemFactory;
import za.ac.cput.service.OrderItemService;
import za.ac.cput.service.OrderService;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {OnlineClothingStoreApp.class})
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderItemControllerTest {

    @Autowired
    private OrderItemController orderItemController;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    private Orders order;
    private OrderItem orderItem;

    @BeforeEach
    void setUp() {
        // Setup a test order
        order = new Orders.Builder()
                .setId(null)
                .setUserID(1L)
                .setAddressID(1L)
                .setStatus("Pending")
                .setTotalPrice(100.0)
                .setOrderDate(LocalDate.now())
                .build();
        order = orderService.create(order); // Save order to get an ID

        // Create a new order item associated with the saved order
        orderItem = OrderItemFactory.buildOrderItem(
                null,
                1L,
                10,
                10.00,
                order
        );

        // Save the orderItem to generate an orderItemID
        orderItem = orderItemService.create(orderItem);
    }

    @AfterEach
    void tearDown() {
        // Cleanup: Deleting the test orderItem from the database
//        if (orderItem != null && orderItem.getId() != null) {
//            orderItemService.deleteById(orderItem.getId());
//        }
    }

    @Test
    @Order(1)
    void createOrderItem() {
        // Arrange
        OrderItem newOrderItem = OrderItemFactory.buildOrderItem(
                null,  // ID will be generated
                1L,
                5,
                5.00,
                order  // The existing order created in the setup
        );

        // Act
        ResponseEntity<OrderItem> response = orderItemController.createOrderItem(newOrderItem);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newOrderItem.getQuantity(), response.getBody().getQuantity());

        // Output for confirmation
        System.out.println(response.getBody());
    }

    @Test
    @Order(2)
    void readOrderItem() {
        // Act
        ResponseEntity<OrderItem> response = orderItemController.getOrderItemById(orderItem.getId());

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderItem.getId(), response.getBody().getId());
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
        ResponseEntity<OrderItem> response = orderItemController.updateOrderItem(orderItem.getId(), updatedOrderItem);

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
        ResponseEntity<Void> response = orderItemController.deleteOrderItem(orderItem.getId());

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that the orderItem is deleted by trying to read it again
        ResponseEntity<OrderItem> readResponse = orderItemController.getOrderItemById(orderItem.getId());
        assertNull(readResponse.getBody());
    }

    @Test
    @Order(5)
    void getAllOrderItems() {
        // Act
        ResponseEntity<List<OrderItem>> response = orderItemController.getAllOrderItems();

        System.out.println(response.getBody());

        // Assert
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Output for confirmation
        System.out.println(response.getBody());
    }
}
