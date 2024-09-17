package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Cart;

import java.time.LocalDate;
import java.util.List;

/**
 * ICartRepository.java
 *
 * Repository interface for managing cart operations.
 * Provides methods to perform CRUD operations and custom queries on carts.
 *
 * Author: Kinzonzi Mukoko
 * Student Num: 221477934
 */
@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {

    /**
     * Finds carts by user ID.
     *
     * @param userID The ID of the user.
     * @return A list of carts for the specified user.
     */
    List<Cart> findByUserID(Long userID);

//    /**
//     * Finds carts by status.
//     *
//     * @param status The status of the carts.
//     * @return A list of carts with the specified status.
//     */
//    List<Cart> findByStatus(String status);

    /**
     * Finds carts within a date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return A list of carts within the specified date range.
     */
    List<Cart> findByCartDateBetween(LocalDate startDate, LocalDate endDate);
//
//    /**
//     * Finds carts by address ID.
//     *
//     * @param addressID The ID of the address.
//     * @return A list of carts for the specified address.
//     */
//    List<Cart> findByAddressID(Long addressID);

    /**
     * Finds carts with a total price greater than the specified amount.
     *
     * @param total_price The minimum total price.
     * @return A list of carts with a total price greater than the specified amount.
     */
    List<Cart> findByTotalPriceGreaterThan(double total_price);

    /**
     * Deletes a cart by its ID.
     *
     * @param id The ID of the cart to delete.
     */
    @Transactional
    @Modifying
    void deleteById(Long id);

//    /**
//     * Finds all carts that contain a specific product ID in their cart items.
//     *
//     * @param productID The ID of the product.
//     * @return A list of carts that include the specified product ID.
//     */
//    @Query("SELECT c FROM Cart c JOIN c.cartItems i WHERE i.productID = :productID")
//    List<Cart> findByProductIDInCartItems(@Param("productID") Long productID);
}
