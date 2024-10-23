package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Discount;

import java.util.List;

@Repository
public interface IDiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findByIdAndMaxUses(Long id, int max_uses);
    List<Discount> findByCode(String code);
    List<Discount> findByDiscountPercentGreaterThan(double percentage);
    void deleteById(Long id);

}
