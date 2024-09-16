package za.ac.cput.service;

import za.ac.cput.domain.Cart;

import java.time.LocalDate;
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
     * @param userID The ID of the user.
     * @return A list of carts associated with the specified user.
     */
    List<Cart> findByUserID(Long userID);

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
    List<Cart> findByCartDateBetween(LocalDate startDate, LocalDate endDate);

    /**
     * Finds carts by the address ID associated with them.
     *
     * @param addressID The ID of the address.
     * @return A list of carts associated with the specified address.
     */
    List<Cart> findByAddressID(Long addressID);

    /**
     * Finds carts with a total price greater than a specified amount.
     *
     * @param totalPrice The minimum total price.
     * @return A list of carts where the total price exceeds the specified amount.
     */
    List<Cart> findByTotalPriceGreaterThan(double totalPrice);

    /**
     * Deletes a cart by its ID.
     *
     * @param cartID The ID of the cart to delete.
     */
    void deleteByCartID(Long cartID);
}
