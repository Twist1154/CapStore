/**
 * E-commerce Web Application for selling T-shirts
 * ReviewController.java
 * This class is the Product Review Controller
 * Author: Mthandeni Mbobo (218223579)
 *
 * */

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Review;
import za.ac.cput.service.ReviewService;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/productReview")
public class ReviewController {

    //
    @Autowired
    private ReviewService productReviewService;

    @PostMapping("/create")
    public Review create(@RequestBody Review review) {
        return productReviewService.create(review);
    }

    @GetMapping("/read/{productReviewId}")
    public ResponseEntity<Review> read(@PathVariable Long productReviewId) {
        Review review = productReviewService.read(productReviewId);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Review> update(@RequestBody Review review) {
        Review updated = productReviewService.update(review);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{productReviewId}")
    public ResponseEntity<Void> delete(@PathVariable Long productReviewId) {
        if (productReviewService.read(productReviewId) != null) {
            productReviewService.delete(productReviewId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public List<Review> getAll() {
        return productReviewService.findAll();
    }

    @GetMapping("/getByRating/{rating}")
    public List<Review> getByRating(@PathVariable int rating) {
        return productReviewService.findByRating(rating);
    }

    //productReviewId
    @GetMapping("/getByProductReviewId/{productReviewId}")
    public Review findByProductReviewId(@PathVariable Long productReviewId) {
        return this.productReviewService.findById(productReviewId);
    }

    //productId
    @GetMapping("/getByProductId/{productId}")
    public List<Review> findByProduct_ProductId(@PathVariable Long productId) {
        return this.productReviewService.findByProduct_Id(productId);
    }

    //userId
    @GetMapping("/getByUserId/{userId}")
    public List<Review> findByUser_UserID(@PathVariable Long userId) {
        return this.productReviewService.findByUser_Id(userId);
    }

}
