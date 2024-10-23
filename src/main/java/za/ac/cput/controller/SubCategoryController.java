package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.SubCategory;
import za.ac.cput.service.SubCategoryService;

import java.util.List;

/**
 * SubCategoryController.java
 * <p>
 * This controller handles HTTP requests for managing SubCategory entities.
 * It provides endpoints for creating, reading, updating, deleting, and listing subcategories.
 * <p>
 * Author: Rethabile Ntsekhe
 * Student Num: 220455430
 * Date: 25-Aug-24
 */
@RestController
@RequestMapping("/subcategory")
@CrossOrigin(origins = "*")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }


    @PostMapping("/create")
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategory subCategory) {
        System.out.println("Received subcategory: " + subCategory);

        try {
            SubCategory createdSubCategory = subCategoryService.create(subCategory);
            return new ResponseEntity<>(createdSubCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long id) {
        try {
            SubCategory subCategory = subCategoryService.read(id);
            if (subCategory != null) {
                return new ResponseEntity<>(subCategory, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long id, @RequestBody SubCategory subCategory) {
        if (!id.equals(subCategory.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            SubCategory updatedSubCategory = subCategoryService.update(subCategory);
            if (updatedSubCategory != null) {
                return new ResponseEntity<>(updatedSubCategory, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        try {
            boolean isDeleted = subCategoryService.delete(id);
            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<SubCategory>> getAllSubCategories() {
        try {
            List<SubCategory> subCategories = subCategoryService.findAll();
            return new ResponseEntity<>(subCategories, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByProduct/{id}")
    public ResponseEntity<List<SubCategory>> getSubCategoriesByProduct(@PathVariable Long id) {
        try {
            List<SubCategory> subCategories = subCategoryService.findAllByProduct_Id(id);
            return new ResponseEntity<>(subCategories, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByCategory/{id}")
    public ResponseEntity<List<SubCategory>> getSubCategoriesByCategory(@PathVariable Long id) {
        try {
            List<SubCategory> subCategories = subCategoryService.findAllByCategory_Id(id);
            return new ResponseEntity<>(subCategories, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
