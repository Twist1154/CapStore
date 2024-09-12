/**
 * E-commerce Web Application for selling T-shirts
 * CategoryController.java
 * This class is the Category Controller
 * Author: Mthandeni Mbobo (218223579)
 *
 * */

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Category;
import za.ac.cput.domain.ProductReview;
import za.ac.cput.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @GetMapping("/read/{categoryId}")
    public ResponseEntity<Category> read(@PathVariable Long categoryId) {
        Category category = categoryService.read(categoryId);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        Category updated = categoryService.update(category);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId) {
        if (categoryService.read(categoryId) != null) {
            categoryService.delete(categoryId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    //categoryName
    @GetMapping("/getByCategoryName/{categoryName}")
    public List<Category> findByCategoryName(@PathVariable String categoryName) {
        return categoryService.findByCategoryName(categoryName);
    }

    //categoryId
    @GetMapping("/getByCategoryId/{categoryId}")
    public List<Category> findByCategoryId(@PathVariable Long categoryId) {
        return categoryService.findByCategoryId(categoryId);
    }
}
