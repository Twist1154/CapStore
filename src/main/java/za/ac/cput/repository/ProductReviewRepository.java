/**
 * E-Commerce Web Application for selling T-shirts
 * ProductReviewRepository.java
 * This class provides the interface for the ProductReview entity
 * Author: Mthandeni Mbobo - 218223579
 * */

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.ProductReview;

import java.util.List;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    List<ProductReview> findByProductReviewId (Long productReviewID);
    List<ProductReview> findByProduct_Id(Long id);
    List<ProductReview> findByUser_UserID (Long userID);
    List<ProductReview> findByRating (int rating);
    boolean deleteByProductReviewId(Long productReviewID);
}
