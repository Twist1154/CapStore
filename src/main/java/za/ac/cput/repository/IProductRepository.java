


package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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

    List<Product> findByName(String name);

    List<Product> findByDescription(String description);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByCreatedAt(LocalDateTime createdAt);

    List<Product> findByUpdatedAt(LocalDateTime updatedAt);
}
