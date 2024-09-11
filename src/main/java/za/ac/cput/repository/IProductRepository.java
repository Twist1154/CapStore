


package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Product;

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
}
