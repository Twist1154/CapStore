package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Orders;

import java.time.LocalDateTime;
import java.util.List;

/**
 * IOrderRepository.java
 *
 * Repository interface for managing order operations.
 * Provides methods to perform CRUD operations and custom queries on orders.
 *
 * Author: Rethabile Ntsekhe
 * Date: 07-Sep-2024
 */
@Repository
public interface IOrderRepository extends JpaRepository<Orders, Long> {

    /**
     * Finds orders by user ID.
     *
     * @param userID The ID of the user.
     * @return A list of orders for the specified user.
     */
    List<Orders> findByUserID(Long userID);

    /**
     * Finds orders by status.
     *
     * @param status The status of the orders.
     * @return A list of orders with the specified status.
     */
    List<Orders> findByStatus(String status);

    /**
     * Finds orders within a date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return A list of orders within the specified date range.
     */
    List<Orders> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Finds orders by address ID.
     *
     * @param addressID The ID of the address.
     * @return A list of orders for the specified address.
     */
    List<Orders> findByAddressID(Long addressID);

    /**
     * Finds orders with a total price greater than the specified amount.
     *
     * @param totalPrice The minimum total price.
     * @return A list of orders with a total price greater than the specified amount.
     */
    List<Orders> findByTotalPriceGreaterThan(double totalPrice);

    /**
     * Deletes an order by its ID.
     *
     * @param orderID The ID of the order to delete.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Orders o WHERE o.orderID = :orderID")
    void deleteByOrderID(@Param("orderID") Long orderID);

    /**
     * Finds all orders that contain a specific product ID in their order items.
     *
     * @param productID The ID of the product.
     * @return A list of orders that include the specified product ID.
     */
    @Query("SELECT o FROM Orders o JOIN o.orderItems i WHERE i.productID = :productID")
    List<Orders> findByProductIDInOrderItems(@Param("productID") Long productID);
}
