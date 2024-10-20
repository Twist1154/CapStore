package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Orders;

import java.time.LocalDate;
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
     * @param id The ID of the user.
     * @return A list of orders for the specified user.
     */
    List<Orders> findByUser_Id(Long id);

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
    List<Orders> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);

    /**
     * Finds orders by address ID.
     *
     * @param id The ID of the address.
     * @return A list of orders for the specified address.
     */
    List<Orders> findByAddress_Id(Long id);

    /**
     * Finds orders with a total price greater than the specified amount.
     *
     * @param total_price The minimum total price.
     * @return A list of orders with a total price greater than the specified amount.
     */
    List<Orders> findByTotalPriceGreaterThan(double total_price);


    /**
     * Deletes an order by its ID.
     *
     * @param id The ID of the order to delete.
     */
    @Transactional
    @Modifying
    void deleteById(Long id);



    /**
     * Finds all orders that contain a specific product ID in their order items.
     *
     * @param productID The ID of the product.
     * @return A list of orders that include the specified product ID.
     */
    @Query("SELECT o FROM Orders o JOIN o.orderItems i WHERE i.product = :productID")
    List<Orders> findByProductIDInOrderItems(@Param("productID") Long productID);
}
