/**
 * E-Commerce Web Application for selling T-shirts
 * ReviewRepository.java
 * This class provides the interface for the Review entity
 * Author: Mthandeni Mbobo - 218223579
 * */

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Review;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findById (Long id);
    List<Review> findByProduct_ProductId(Long productID);
    List<Review> findByUser_UserID (Long userID);
    List<Review> findByRating (int rating);
    void deleteById(Long id);
}
