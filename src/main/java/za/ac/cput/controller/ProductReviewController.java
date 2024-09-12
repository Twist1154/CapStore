/**
 * E-commerce Web Application for selling T-shirts
 * ProductReviewController.java
 * This class is the Product Review Controller
 * Author: Mthandeni Mbobo (218223579)
 *
 * */

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ProductReview;
import za.ac.cput.service.ProductReviewService;

import java.util.List;

@RestController
@RequestMapping("/productReview")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    @PostMapping("/create")
    public ProductReview create(@RequestBody ProductReview productReview) {
        return productReviewService.create(productReview);
    }

    @GetMapping("/read/{productReviewId}")
    public ResponseEntity<ProductReview> read(@PathVariable Long productReviewId) {
        ProductReview productReview = productReviewService.read(productReviewId);
        if (productReview != null) {
            return ResponseEntity.ok(productReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ProductReview> update(@RequestBody ProductReview productReview) {
        ProductReview updated = productReviewService.update(productReview);
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
    public List<ProductReview> getAll() {
        return productReviewService.findAll();
    }

    @GetMapping("/getByRating/{rating}")
    public List<ProductReview> getByRating(@PathVariable int rating) {
        return productReviewService.findByRating(rating);
    }

    //productReviewId
    @GetMapping("/getByProductReviewId/{productReviewId}")
    public List<ProductReview> findByProductReviewId(@PathVariable Long productReviewId) {
        return this.productReviewService.findByProductReviewId(productReviewId);
    }

    //customerName
    @GetMapping("/getByCustomerName/{customerName}")
    public List<ProductReview> findByCustomerName(@PathVariable String customerName) {
        return this.productReviewService.findByCustomerName(customerName);
    }

    //productName
    @GetMapping("/getByProductName/{productName}")
    public List<ProductReview> findByProductName(@PathVariable String productName) {
        return this.productReviewService.findByProductName(productName);
    }
}
