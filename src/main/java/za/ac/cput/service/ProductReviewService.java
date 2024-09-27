/**
 * E-Commerce Web Application for selling T-shirts
 * ProductReviewService.java
 * This class provides the service for the ProductReview entity
 * Author: Mthandeni Mbobo - 218223579
 * */

package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ProductReview;
import za.ac.cput.repository.ProductReviewRepository;

import java.util.List;

@Service
public class ProductReviewService implements IProductReviewService{

    private final ProductReviewRepository productReviewRepository;

    @Autowired
    public ProductReviewService(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    @Override
    public ProductReview create(ProductReview productReview) {
        return this.productReviewRepository.save(productReview);
    }

    @Override
    public ProductReview read(Long productReviewId) {
        return this.productReviewRepository.findById(productReviewId).orElse(null);
    }

    @Override
    public ProductReview update(ProductReview productReview) {
        if (this.productReviewRepository.existsById(productReview.getProductReviewId())) {
            return this.productReviewRepository.save(productReview);
        }
        return null;
    }

    @Override
    public boolean delete(Long productReviewId) {
        if (this.productReviewRepository.existsById(productReviewId)) {
            this.productReviewRepository.deleteById(productReviewId);
            return !this.productReviewRepository.existsById(productReviewId);
        }
        return false;
    }

    @Override
    public List<ProductReview> findAll() {
        return this.productReviewRepository.findAll();
    }

    @Override
    public List<ProductReview> findByProductReviewId(Long productReviewID) {
        return this.productReviewRepository.findByProductReviewId(productReviewID);
    }

    @Override
    public List<ProductReview> findByProduct_ProductId(Long productId) {
        return this.productReviewRepository.findByProduct_ProductId(productId);
    }

    @Override
    public List<ProductReview> findByUser_UserID(Long userID) {
        return this.productReviewRepository.findByUser_UserID(userID);
    }

    @Override
    public List<ProductReview> findByRating(int rating) {
        return this.productReviewRepository.findByRating(rating);
    }
}
