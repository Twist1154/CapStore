package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Orders;
import za.ac.cput.repository.IOrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * OrderService.java
 * <p>
 * Service class to manage Orders.
 * <p>
 * Author: Rethabile Ntsekhe
 * Student Num: 220455430
 * Date: 07-Sep-24
 */

@Service
@Transactional
public class OrderService implements IOrderService {
    private final IOrderRepository repository;
    private static final Logger logger = Logger.getLogger(OrderService.class.getName());

    @Autowired
    public OrderService(IOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Orders create(Orders orders) {
        return repository.save(orders);
    }

    @Override
    public Orders read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Orders update(Orders orders) {
        return repository.findById(orders.getId()).map(existingOrder -> {
            Orders updatedOrder = new Orders.Builder()
                    .copy(existingOrder)
                    .setUserID(orders.getUserID())
                    .setAddressID(orders.getAddressID())
                    .setOrderDate(orders.getOrderDate())
                    .setTotalPrice(orders.getTotalPrice())
                    .setStatus(orders.getStatus())
                    .setOrderItems(orders.getOrderItems())
                    .build();
            return repository.save(updatedOrder);
        }).orElseGet(() -> {
            logger.warning("Attempt to update non-existing order with ID: " + orders.getId());
            return null;
        });
    }

    @Override
    public List<Orders> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Orders> findByUserID(Long userID) {
        return repository.findByUserID(userID);
    }

    @Override
    public List<Orders> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<Orders> findByOrderDateBetween(LocalDate startDate, LocalDate endDate) {
        return repository.findByOrderDateBetween(startDate, endDate);
    }

    @Override
    public List<Orders> findByAddressID(Long addressID) {
        return repository.findByAddressID(addressID);
    }

    @Override
    public List<Orders> findByTotalPriceGreaterThan(double totalPrice) {
        return repository.findByTotalPriceGreaterThan(totalPrice);
    }

    @Override
    public void deleteByOrderID(Long orderID) {
        if (repository.existsById(orderID)) {
            repository.deleteById(orderID);
        } else {
            logger.warning("Attempt to delete non-existent order with ID: " + orderID);
        }
    }
}