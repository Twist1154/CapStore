package za.ac.cput.service;

import za.ac.cput.domain.Orders;

import java.time.LocalDate;
import java.util.List;

/**
 * IOrderService.java
 *
 * Service interface for managing Orders.
 * Provides additional methods for specific queries on Orders besides the basic CRUD operations.
 *
 * Author: Rethabile Ntsekhe
 * Student Num: 220455430
 * Date: 07-Sep-24
 */

public interface IOrderService extends IService<Orders, Long> {

    /**
     * Finds orders by user ID.
     *
     * @param id The ID of the user.
     * @return A list of orders associated with the specified user.
     */
    List<Orders> findByUsers_Id(Long id);

    /**
     * Finds orders by their status.
     *
     * @param status The status of the orders (e.g., "Pending", "Shipped", "Completed").
     * @return A list of orders with the specified status.
     */
    List<Orders> findByStatus(String status);

    /**
     * Finds orders placed within a specific date range.
     *
     * @param startDate The start of the date range.
     * @param endDate   The end of the date range.
     * @return A list of orders placed within the given date range.
     */
    List<Orders> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);

    /**
     * Finds orders by the address ID associated with them.
     *
     * @param addressID The ID of the address.
     * @return A list of orders associated with the specified address.
     */
    List<Orders> findByAddressID(Long addressID);

    /**
     * Finds orders with a total price greater than a specified amount.
     *
     * @param totalPrice The minimum total price.
     * @return A list of orders where the total price exceeds the specified amount.
     */
    List<Orders> findByTotalPriceGreaterThan(double totalPrice);

    /**
     * Deletes an order by its ID.
     *
     * @param orderID The ID of the order to delete.
     */
    void deleteByOrderID(Long orderID);
}
