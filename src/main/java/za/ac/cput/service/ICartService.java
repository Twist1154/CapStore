package za.ac.cput.service;

import za.ac.cput.domain.Cart;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ICartService.java
 *
 * Service interface for managing Carts.
 * Provides additional methods for specific queries on Carts besides the basic CRUD operations.
 *
 * Author: Kinzonzi Mukoko
 * Student Num: 221477934
 * Date: 07-Sep-24
 */
public interface ICartService extends IService<Cart, Long> {

    /**
     * Finds carts by user ID.
     *
     * @param id The ID of the user.
     * @return A list of carts associated with the specified user.
     */
    List<Cart> findByUsers_Id(Long id);

    /**
     * Finds carts by their status.
     *
     * @param status The status of the carts (e.g., "Pending", "Shipped", "Completed").
     * @return A list of carts with the specified status.
     */
    List<Cart> findByStatus(String status);

    /**
     * Finds carts placed within a specific date range.
     *
     * @param startDate The start of the date range.
     * @param endDate   The end of the date range.
     * @return A list of carts placed within the given date range.
     */
    List<Cart> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);


    /**
     * Finds carts with a total price greater than a specified amount.
     *
     * @param total The minimum total price.
     * @return A list of carts where the total price exceeds the specified amount.
     */
    List<Cart> findByTotalGreaterThan(double total);

    /**
     * Deletes a cart by its ID.
     *
     * @param id The ID of the cart to delete.
     */
    void deleteByCartID(Long id);
}
