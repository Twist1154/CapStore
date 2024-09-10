/**
 * E-Commerce Web Application for selling T-shirts
 * IProductReviewService.java
 * This class provides the interface for the ProductReview entity
 * Author: Mtandeni Mbobo - 218223579
 * */

package za.ac.cput.service;

import za.ac.cput.domain.ProductReview;

import java.util.List;

public interface IProductReviewService extends IService<ProductReview, Long> {

    List<ProductReview> findByProductReviewId (Long productReviewID);
    List<ProductReview> findByProductName (String productName);
    List<ProductReview> findByCustomerName (String customerName);
    List<ProductReview> findByRating (int rating);

    boolean delete(Long productReviewID);
}
