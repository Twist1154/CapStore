/**
 * E-Commerce Web Application for selling T-shirts
 * CategoryService.java
 * This class provides the service for the Category entity
 * Author: Mthandeni Mbobo - 218223579
 */

package za.ac.cput.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Category;
import za.ac.cput.repository.CategoryRepository;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category read(Long categoryId) {
        return this.categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public Category update(Category category) {
        Category existingCategory = read(category.getId());
        if (existingCategory != null) {
            Category updatedCategory = new Category.Builder()
                    .copy(category)
                    .setId(existingCategory.getId())
                    .setName(category.getName())
                    .build();
            return categoryRepository.save(updatedCategory);
        }
        log.error("Attempted to update a non-existent Category with ID: {}", category.getId());
        return null;
    }

    @Override
    public boolean delete(Long categoryId) {
        if (this.categoryRepository.existsById(categoryId)) {
            this.categoryRepository.deleteById(categoryId);
            return !this.categoryRepository.existsById(categoryId);
        }
        return false;
    }


    @Override
    public List<Category> findByName(String categoryName) {
        return this.categoryRepository.findByName(categoryName);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }


}
