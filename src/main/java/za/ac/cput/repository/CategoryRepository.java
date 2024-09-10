/**
 * E-Commerce Web Application for selling T-shirts
 * CategoryRepository.java
 * This class provides the interface for the Category entity
 * Author: Mthandeni Mbobo - 218223579
 * */

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByCategoryId (Long categoryId);
    List<Category> findByCategoryName (String categoryName);

    boolean deleteByCategoryId(Long categoryId);

}
