/**
 * E-commerce Web Application for selling T-shirts
 * ReviewController.java
 *
 * This class is the Product Review Controller
 * Author: Mthandeni Mbobo (218223579)
 *
 */

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Review;
import za.ac.cput.service.ReviewService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Create a new review
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Review review) {
        try {
            System.out.println("Received review: " + review); // Log the received review
            Review newReview = reviewService.create(review);
            return new ResponseEntity<>(newReview, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error creating review: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Error creating review: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a review by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Review> read(@PathVariable Long id) {
        Review review = reviewService.read(id);
        // If review exists, return it with HTTP status 200 (OK); otherwise, return 404 (NOT FOUND)
        return review != null ? ResponseEntity.ok(review) : ResponseEntity.notFound().build();
    }

    // Update an existing review
    @PutMapping("/update")
    public ResponseEntity<Review> update(@RequestBody Review review) {
        Review updatedReview = reviewService.update(review);
        // If review was updated successfully, return it with HTTP status 200 (OK); otherwise, return 404 (NOT FOUND)
        return updatedReview != null ? ResponseEntity.ok(updatedReview) : ResponseEntity.notFound().build();
    }

    // Delete a review by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Check if the review exists before attempting to delete
        if (reviewService.read(id) != null) {
            reviewService.delete(id);
            // Return 204 (NO CONTENT) to indicate successful deletion
            return ResponseEntity.noContent().build();
        } else {
            // Return 404 (NOT FOUND) if the review doesn't exist
            return ResponseEntity.notFound().build();
        }
    }

    // Get all reviews
    @GetMapping("/getAll")
    public List<Review> getAll() {
        // Return the list of all reviews
        return reviewService.findAll();
    }

    // Get reviews by rating
    @GetMapping("/getByRating/{rating}")
    public List<Review> getByRating(@PathVariable int rating) {
        // Return the list of reviews filtered by rating
        return reviewService.findByRating(rating);
    }

    // Get a review by product ID
    @GetMapping("/getByProductId/{productId}")
    public ResponseEntity<List<Review>> findByProduct_ProductId(@PathVariable Long productId) {
        List<Review> reviews = reviewService.findByProduct_ProductId(productId);
        // Return the list of reviews for the specified product or 404 (NOT FOUND) if empty
        return reviews.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(reviews);
    }

    // Get reviews by user ID
    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<Review>> findByUser_UserID(@PathVariable Long userId) {
        List<Review> reviews = reviewService.findByUser_UserID(userId);
        // Return the list of reviews by the specified user or 404 (NOT FOUND) if empty
        return reviews.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(reviews);
    }
}
