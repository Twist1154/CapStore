package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Discount;
import za.ac.cput.service.DiscountService;

import java.util.List;

/**
 * DiscountController.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 22-Oct-24
 */
@RestController
@RequestMapping("/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    // Create a discount
    @PostMapping("/create")
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount) {
        Discount createdDiscount = discountService.create(discount);
        return new ResponseEntity<>(createdDiscount, HttpStatus.CREATED);
    }

    // Read a discount by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Discount> readDiscount(@PathVariable Long id) {
        Discount discount = discountService.read(id);
        if (discount != null) {
            return new ResponseEntity<>(discount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a discount
    @PutMapping("/update")
    public ResponseEntity<Discount> updateDiscount(@RequestBody Discount discount) {
        Discount updatedDiscount = discountService.update(discount);
        if (updatedDiscount != null) {
            return new ResponseEntity<>(updatedDiscount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Find discounts by ID and max uses
    @GetMapping("/findByIdAndMaxUses/{id}/{maxUses}")
    public ResponseEntity<List<Discount>> findByIdAndMaxUses(@PathVariable Long id, @PathVariable int maxUses) {
        List<Discount> discounts = discountService.findByIdAndMaxUses(id, maxUses);
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    // Find discounts by code
    @GetMapping("/findByCode/{code}")
    public ResponseEntity<List<Discount>> findByCode(@PathVariable String code) {
        List<Discount> discounts = discountService.findByCode(code);
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    // Find discounts with a discount percent greater than the provided value
    @GetMapping("/findByDiscountPercentGreaterThan/{percentage}")
    public ResponseEntity<List<Discount>> findByDiscountPercentGreaterThan(@PathVariable double percentage) {
        List<Discount> discounts = discountService.findByDiscountPercentGreaterThan(percentage);
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    // Delete a discount by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        discountService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Find all discounts
    @GetMapping("/findAll")
    public ResponseEntity<List<Discount>> findAllDiscounts() {
        List<Discount> discounts = discountService.findAll();
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }
}
