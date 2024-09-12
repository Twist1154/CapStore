package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Orders;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderItemFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrdersServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    private Orders order;

    @BeforeEach
    void setUp() {
        // Create an initial order
        order = OrderFactory.buildOrder(
                null,  // ID should be null for auto-generation
                2L,    // userID
                5L,    // addressID
                "Pending",
                150.0,
                LocalDate.now(),
                new ArrayList<>()
        );

        // Save the order to generate an orderID
        order = orderService.create(order);

        // Create OrderItems
        OrderItem orderItem1 = OrderItemFactory.buildOrderItem(
                null,
                order.getId(),
                12,
                12.00,
                order
        );

        OrderItem orderItem2 = OrderItemFactory.buildOrderItem(
                null,
                order.getId(),
                5,
                10.00,
                order
        );

        OrderItem orderItem3 = OrderItemFactory.buildOrderItem(
                null,
                order.getId(),
                20,
                20.00,
                order
        );

        // Save OrderItems
        orderItem1 = orderItemService.create(orderItem1);
        orderItem2 = orderItemService.create(orderItem2);
        orderItem3 = orderItemService.create(orderItem3);

        // Ensure OrderItems are associated with the Order
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderItems.add(orderItem3);

        order = new Orders.Builder()
                .copy(order)
                .setOrderItems(orderItems)  // Add OrderItems
                .build();

        order = orderService.update(order); // Ensure the updated order is persisted
    }

    @Test
    @Order(1)
    void create() {
        Orders newOrder = OrderFactory.buildOrder(
                null,
                2L,
                5L,
                "Pending",
                200.0,
                LocalDate.now(),
                new ArrayList<>()
        );

        Orders createdOrder = orderService.create(newOrder);
        assertNotNull(createdOrder);
        assertNotNull(createdOrder.getId());  // Check if ID is generated
        System.out.println("Created: \n" + createdOrder + "\n");
    }

    @Test
    @Order(2)
    void read() {
        Orders readOrder = orderService.read(order.getId());
        assertNotNull(readOrder);
        assertEquals(order.getId(), readOrder.getId());
        System.out.println("Read: \n" + readOrder + "\n");

        // Debugging OrderItems
        List<OrderItem> orderItems = readOrder.getOrderItems();
        System.out.println("Order Items: " + orderItems);
    }

    @Test
    @Order(3)
    void update() {
        Orders updatedOrder = new Orders.Builder()
                .copy(order)
                .setUserID(3L) // Updated userID
                .setTotalPrice(300.0) // Updated totalPrice
                .setStatus("Shipped") // Updated status
                .build();

        Orders result = orderService.update(updatedOrder);
        assertNotNull(result);
        assertEquals(updatedOrder.getTotalPrice(), result.getTotalPrice());
        assertEquals(updatedOrder.getStatus(), result.getStatus());
        System.out.println("Updated: \n" + result + "\n");
    }

    @Test
    @Order(4)
    void getAll() {
        List<Orders> orders = orderService.findAll();
        assertFalse(orders.isEmpty());
        System.out.println("All Orders: \n" + orders + "\n");
    }

    @Test
    @Order(5)
    void delete() {
        Long orderIdToDelete = order.getId();
        orderService.deleteByOrderID(orderIdToDelete);

        Orders deletedOrder = orderService.read(orderIdToDelete);
        assertNull(deletedOrder);
        System.out.println("Deleted Order ID: " + orderIdToDelete);
    }
}
