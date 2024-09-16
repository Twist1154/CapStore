package za.ac.cput.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Orders;
import za.ac.cput.service.OrderService;

import java.time.LocalDate;
import java.util.List;

/**
 * OrderController.java
 * <p>
 * Controller class to handle HTTP requests for Orders.
 * <p>
 * Author: Rethabile Ntsekhe
 * Student Num: 220455430
 * Date: 07-Sep-24
 */

@CrossOrigin(origins = "*")
@RestController
@Transactional
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new order
    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders orders) {
        try {
            if (orders == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Orders newOrder = orderService.create(orders);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating order", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read an order by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Orders> read(@PathVariable Long id) {
        try {
            Orders order = orderService.read(id);
            if (order != null) {
                return new ResponseEntity<>(order, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error reading order with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing order
    @PutMapping("/update/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders orders) {
        try {
            Orders existingOrder = orderService.read(id);
            if (existingOrder == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Orders updatedOrder = orderService.update(orders);
            return updatedOrder != null ? ResponseEntity.ok(updatedOrder) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error updating order with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete an order by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            Orders existingOrder = orderService.read(id);
            if (existingOrder == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            orderService.deleteByOrderID(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);  // Change made here
        } catch (Exception e) {
            logger.error("Error deleting order with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Get all orders
    @GetMapping("/all")
    public ResponseEntity<List<Orders>> getAllOrders() {
        try {
            List<Orders> ordersList = orderService.findAll();
            if (ordersList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(ordersList);
        } catch (Exception e) {
            logger.error("Error fetching all orders", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get orders by userID
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<Orders>> getOrdersByUserID(@PathVariable Long userID) {
        try {
            List<Orders> ordersList = orderService.findByUserID(userID);
            if (ordersList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(ordersList);
        } catch (Exception e) {
            logger.error("Error fetching orders for userID " + userID, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get orders by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Orders>> getOrdersByStatus(@PathVariable String status) {
        try {
            List<Orders> ordersList = orderService.findByStatus(status);
            if (ordersList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(ordersList);
        } catch (Exception e) {
            logger.error("Error fetching orders with status " + status, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get orders by date range
    @GetMapping("/date-range")
    public ResponseEntity<List<Orders>> getOrdersByDateRange(@RequestParam LocalDate startDate,
                                                             @RequestParam LocalDate endDate) {
        try {
            if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<Orders> ordersList = orderService.findByOrderDateBetween(startDate, endDate);
            if (ordersList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(ordersList);
        } catch (Exception e) {
            logger.error("Error fetching orders between dates " + startDate + " and " + endDate, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get orders by addressID
    @GetMapping("/address/{addressID}")
    public ResponseEntity<List<Orders>> getOrdersByAddressID(@PathVariable Long addressID) {
        try {
            List<Orders> orders = orderService.findByAddressID(addressID);
            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching orders with addressID " + addressID, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get orders with a total price greater than a specified value
    @GetMapping("/total-price/{totalPrice}")
    public ResponseEntity<List<Orders>> getOrdersByTotalPriceGreaterThan(@PathVariable double totalPrice) {
        logger.info("Fetching orders with totalPrice greater than: " + totalPrice);
        try {
            if (totalPrice < 0) {
                logger.warn("Invalid totalPrice value: " + totalPrice);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            List<Orders> ordersList = orderService.findByTotalPriceGreaterThan(totalPrice);
            logger.info("Fetched " + ordersList.size() + " orders.");

            if (ordersList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.ok(ordersList);
        } catch (Exception e) {
            logger.error("Error fetching orders with totalPrice greater than " + totalPrice, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint to add an item to an existing order and update the total price.
     *
     * @param orderId   The ID of the order to which the item is to be added.
     * @param orderItem The item to be added to the order.
     * @return The updated order with the new item and recalculated total price.
     */
    @PostMapping("/{orderId}/add-item")
    public ResponseEntity<Orders> addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        Orders updatedOrder = orderService.addOrderItem(orderId, orderItem);

        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    /**
     * Endpoint to remove an item from an existing order and update the total price.
     *
     * @param orderId   The ID of the order from which the item is to be removed.
     * @param orderItem The item to be removed from the order.
     * @return The updated order with the item removed and recalculated total price.
     */
    @PostMapping("/{orderId}/remove-item")
    public ResponseEntity<Orders> removeOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        Orders updatedOrder = orderService.removeOrderItem(orderId, orderItem);

        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}
