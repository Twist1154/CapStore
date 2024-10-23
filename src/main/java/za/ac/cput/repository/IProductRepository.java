package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

/**
 * IProductRepository.java
 *
 * @author Zachariah Matsimella
 * Student Num: 220097429
 * @date 06-Sep-24
 */

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Override
    void deleteById(Long id);

    @Query("SELECT p from Product p WHERE "+
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findByName(String name);

    @Query("SELECT p from Product p WHERE "+
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Product> findByDescription(String description);

    List<Product> findByCategories_Id(Long id);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByCreatedAt(LocalDateTime createdAt);

    List<Product> findByUpdatedAt(LocalDateTime updatedAt);

    @Query("SELECT p from Product p WHERE "+
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyWord, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyWord, '%'))")
    List<Product> searchProducts(String keyWord);
}
