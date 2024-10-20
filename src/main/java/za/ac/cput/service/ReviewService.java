/**
 * E-Commerce Web Application for selling T-shirts
 * ReviewService.java
 * This class provides the service for the Review entity
 * Author: Mthandeni Mbobo - 218223579
 * */

package za.ac.cput.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Review;
import za.ac.cput.repository.ReviewRepository;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review create(Review review) {
        return this.reviewRepository.save(review);
    }

    @Override
    @Transactional(readOnly = true)
    public Review read(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review update(Review review) {
        Review existingReview = read(review.getId());
        if (existingReview != null) {
            Review updatedReview = new Review.Builder()
                    .copy(review)
                    .setId(existingReview.getId())
                    .setProduct(existingReview.getProduct())
                    .setUser(existingReview.getUser())
                    .setRating(review.getRating())
                    .setComment(review.getComment())
                    .build();
            return reviewRepository.save(updatedReview);
        }
        log.error("Review with id  not found", review.getId());
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if (this.reviewRepository.existsById(id)) {
            this.reviewRepository.deleteById(id);
            return !this.reviewRepository.existsById(id);
        }
        return false;
    }

    @Override
    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    @Override
    public Review findById(Long id) {
        return this.reviewRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review> findByProduct_Id(Long product_id) {
        return reviewRepository.findByProduct_Id(product_id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review> findByUser_Id(Long user_id) {
        return reviewRepository.findByUser_Id(user_id);
    }

    @Override
    public List<Review> findByRating(int rating) {
        return this.reviewRepository.findByRating(rating);
    }
}
