package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Orders;
import za.ac.cput.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OrderController.java
 *
 * Controller class to handle HTTP requests for Orders.
 *
 * Author: Rethabile Ntsekhe
 * Student Num: 220455430
 * Date: 07-Sep-24
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new order
    @PostMapping("/create/{order}")
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
            if (orders == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Orders existingOrder = orderService.read(id);
            if (existingOrder == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Orders updatedOrder = orderService.update(orders);
            return ResponseEntity.ok(updatedOrder);
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
            return ResponseEntity.noContent().build();
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
    public ResponseEntity<List<Orders>> getOrdersByDateRange(@RequestParam LocalDateTime startDate,
                                                             @RequestParam LocalDateTime endDate) {
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
    public ResponseEntity<List<Orders>> getOrdersByTotalPriceGreaterThan(@RequestParam double totalPrice) {
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
}
