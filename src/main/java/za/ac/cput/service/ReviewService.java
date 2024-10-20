/**
 * E-Commerce Web Application for selling T-shirts
 * ReviewService.java
 * This class provides the service for the Review entity
 * Author: Mthandeni Mbobo - 218223579
 * */

package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;
import za.ac.cput.repository.IProductRepository;
import za.ac.cput.repository.ReviewRepository;
import za.ac.cput.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final IProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, IProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Review create(Review review) {
        // Fetch and set product if exists
        if (review.getProduct() != null && review.getProduct().getProductId() != null) {
            Optional<Product> product = productRepository.findById(review.getProduct().getProductId());
            product.ifPresent(review::setProduct);
        }

        // Fetch and set user if exists
        if (review.getUser() != null && review.getUser().getUserID() != null) {
            Optional<User> user = userRepository.findById(review.getUser().getUserID());
            user.ifPresent(review::setUser);
        }

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
    public List<Review> findByProduct_ProductId(Long productId) {
        return this.reviewRepository.findByProduct_ProductId(productId);
    }

    @Override
    public List<Review> findByUser_UserID(Long userID) {
        return this.reviewRepository.findByUser_UserID(userID);
    }

    @Override
    public List<Review> findByRating(int rating) {
        return this.reviewRepository.findByRating(rating);
    }
}