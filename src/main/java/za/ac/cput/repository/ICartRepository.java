package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Cart;

import java.time.LocalDateTime;
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
     * @param id The ID of the user.
     * @return A list of carts for the specified user.
     */
    List<Cart> findByUsers_Id(Long id);



    /**
     * Finds carts within a date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return A list of carts within the specified date range.
     */
    List<Cart> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);


    /**
     * Finds carts with a total price greater than the specified amount.
     *
     * @param total The minimum total price.
     * @return A list of carts with a total price greater than the specified amount.
     */
    List<Cart> findByTotalGreaterThan(double total);

    /**
     * Deletes a cart by its ID.
     *
     * @param id The ID of the cart to delete.
     */
    @Transactional
    @Modifying
    void deleteById(Long id);
}
