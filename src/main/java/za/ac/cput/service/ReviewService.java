/**
 * E-Commerce Web Application for selling T-shirts
 * ReviewService.java
 *
 * This class provides the service for the Review entity
 * Author: Mthandeni Mbobo - 218223579
 * */

package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Review;
import za.ac.cput.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
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
    public Review read(Long id) {
        return this.reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review update(Review review) {
        if (this.reviewRepository.existsById(review.getId())) {
            return this.reviewRepository.save(review);
        }
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
    public Optional<Review> findById(Long id) {
        return this.reviewRepository.findById(id);
    }

    @Override
    public List<Review> findByProduct_Id(Long productId) {
        return this.reviewRepository.findByProduct_Id(productId);
    }

    @Override
    public List<Review> findByUsers_Id(Long userID) {
        return this.reviewRepository.findByUsers_Id(userID);
    }

    @Override
    public List<Review> findByRating(int rating) {
        return this.reviewRepository.findByRating(rating);
    }
}