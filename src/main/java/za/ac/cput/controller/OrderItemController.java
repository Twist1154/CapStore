package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.service.IOrderItemService;

import java.util.List;
import java.util.Optional;

/**
 * OrderItemController.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 09-Sep-24
 */

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    private final IOrderItemService orderItemService;

    @Autowired
    public OrderItemController(IOrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem createdOrderItem = orderItemService.create(orderItem);
        if (createdOrderItem != null) {
            return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItem> orderItem = Optional.ofNullable(orderItemService.read(id));
        return orderItem.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        if (!id.equals(orderItem.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        OrderItem updatedOrderItem = orderItemService.update(orderItem);
        return updatedOrderItem != null ? ResponseEntity.ok(updatedOrderItem) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.findAll();
        return ResponseEntity.ok(orderItems);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        if (orderItemService.read(id) != null) {
            orderItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/order_id/{id}")
    public ResponseEntity<List<OrderItem>> findAllByOrder_Id(@PathVariable Long id) {
        List<OrderItem> orderItems = orderItemService.findAllByOrder_Id(id);
        if (orderItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }
}
