package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.CartItem;

/**
 * ICartItemRepository.java
 *
 * Author: Kinzonzi Mukoko
 * Student Num: 221477934
 * Date: 09-Sep-24
 */
@Repository
public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    @Override
    void deleteById(Long id);
}
