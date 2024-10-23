/**
 * E-Commerce Web Application for selling T-shirts
 * IReviewService.java
 *
 * This class provides the interface for the Review entity
 * Author: Mtandeni Mbobo - 218223579
 * */

package za.ac.cput.service;

import za.ac.cput.domain.Review;

import java.util.List;
import java.util.Optional;

public interface IReviewService extends IService<Review, Long> {

    Optional<Review> findById (Long id);
    List<Review> findByProduct_Id(Long productId);
    List<Review> findByUsers_Id(Long userID);

    List<Review> findByRating (int rating);
    boolean delete(Long id);
}
