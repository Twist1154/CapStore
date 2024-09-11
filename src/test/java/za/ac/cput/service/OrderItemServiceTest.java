package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Orders;
import za.ac.cput.repository.IOrderItemRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderItemServiceTest {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private IOrderItemRepository orderItemRepository;

    @Autowired
    private OrderService orderService; // Add OrderService to link OrderItem with Order

    private Orders testOrder;

    @BeforeEach
    void setUp() {
        // Setup a test order
        testOrder = new Orders.Builder()
                .setUserID(1L)
                .setAddressID(1L)
                .setStatus("Pending")
                .setTotalPrice(100.0)
                .setOrderDate(LocalDate.now())
                .build();
        testOrder = orderService.create(testOrder); // Save order to get an ID
    }

    @AfterEach
    void tearDown() {
        // Clean up data after each test
        orderItemRepository.deleteAll();
        orderService.deleteByOrderID(testOrder.getOrderID()); // Clean up test order
    }

    @Test
    @Order(1)
    void create() {
        System.out.println("Create");
        OrderItem orderItem = new OrderItem.Builder()
                .setProductID(1L)
                .setQuantity(5)
                .setPrice(100.0)
                .setOrder_id(testOrder.getOrderID()) // Link to the test order
                .build();

        OrderItem createdOrderItem = orderItemService.create(orderItem);
        System.out.println(createdOrderItem);
        assertNotNull(createdOrderItem);
        assertNotNull(createdOrderItem.getOrderItemID());
        assertEquals(1L, createdOrderItem.getProductID());
    }

    @Test
    @Order(2)
    void read() {
        System.out.println("find By Id/ read");
        OrderItem orderItem = new OrderItem.Builder()
                .setProductID(2L)
                .setQuantity(10)
                .setPrice(200.0)
                .setOrder_id(testOrder.getOrderID()) // Link to the test order
                .build();

        OrderItem createdOrderItem = orderItemService.create(orderItem);
        System.out.println(createdOrderItem);
        OrderItem readOrderItem = orderItemService.read(createdOrderItem.getOrderItemID());
        System.out.println(readOrderItem);
        assertNotNull(readOrderItem);
        assertEquals(createdOrderItem.getOrderItemID(), readOrderItem.getOrderItemID());
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("Update");
        OrderItem orderItem = new OrderItem.Builder()
                .setProductID(3L)
                .setQuantity(15)
                .setPrice(300.0)
                .setOrder_id(testOrder.getOrderID()) // Link to the test order
                .build();

        OrderItem createdOrderItem = orderItemService.create(orderItem);
        System.out.println(createdOrderItem);
        OrderItem updatedOrderItem = new OrderItem.Builder()
                .setOrderItemID(createdOrderItem.getOrderItemID())
                .setProductID(3L) // unchanged
                .setQuantity(20)
                .setPrice(350.0)
                .setOrder_id(testOrder.getOrderID()) // Link to the test order
                .build();
        System.out.println(updatedOrderItem);
        OrderItem result = orderItemService.update(updatedOrderItem);
        assertNotNull(result);
        assertEquals(20, result.getQuantity());
        assertEquals(350.0, result.getPrice());
    }

    @Test
    @Order(4)
    void findAll() {
        System.out.println("find All");
        OrderItem orderItem1 = new OrderItem.Builder()
                .setProductID(4L)
                .setQuantity(10)
                .setPrice(400.0)
                .setOrder_id(testOrder.getOrderID()) // Link to the test order
                .build();

        OrderItem orderItem2 = new OrderItem.Builder()
                .setProductID(5L)
                .setQuantity(5)
                .setPrice(500.0)
                .setOrder_id(testOrder.getOrderID()) // Link to the test order
                .build();

        orderItemService.create(orderItem1);
        orderItemService.create(orderItem2);

        List<OrderItem> orderItems = orderItemService.findAll();
        System.out.println(orderItems);
        assertNotNull(orderItems);
        assertEquals(2, orderItems.size());
    }

    @Test
    @Order(5)
    void findById() {
        OrderItem orderItem = new OrderItem.Builder()
                .setProductID(7L)
                .setQuantity(8)
                .setPrice(700.0)
                .setOrder_id(testOrder.getOrderID()) // Link to the test order
                .build();
        System.out.println("find By Id");
        OrderItem createdOrderItem = orderItemService.create(orderItem);
        System.out.println(createdOrderItem);
        Optional<OrderItem> foundOrderItem = orderItemService.findById(createdOrderItem.getOrderItemID());
        System.out.println(foundOrderItem);
        assertTrue(foundOrderItem.isPresent());
        assertEquals(createdOrderItem.getOrderItemID(), foundOrderItem.get().getOrderItemID());
    }

    @Test
    @Order(6)
    void deleteById() {
        System.out.println("Delete By Id");
        OrderItem orderItem = new OrderItem.Builder()
                .setProductID(6L)
                .setQuantity(7)
                .setPrice(600.0)
                .setOrder_id(testOrder.getOrderID()) // Link to the test order
                .build();

        OrderItem createdOrderItem = orderItemService.create(orderItem);
        System.out.println(createdOrderItem);
        orderItemService.deleteById(createdOrderItem.getOrderItemID());

        OrderItem deletedOrderItem = orderItemService.read(createdOrderItem.getOrderItemID());
        assertNull(deletedOrderItem);
    }
}
