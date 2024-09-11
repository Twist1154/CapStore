/**
 * E-Commerce Web Application for selling T-shirts
 * CategoryService.java
 * This class provides the service for the Category entity
 * Author: Mthandeni Mbobo - 218223579
 * */

package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Category;
import za.ac.cput.repository.CategoryRepository;

import java.util.List;

@Service
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
        if (this.categoryRepository.existsById(category.getCategoryId())) {
            return this.categoryRepository.save(category);
        }
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
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<Category> findByCategoryId(Long categoryId) {
        return this.categoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Category> findByCategoryName(String categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName);
    }

}
