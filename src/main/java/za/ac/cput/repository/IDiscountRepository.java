package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Discount;

@Repository
public interface IDiscountRepository extends JpaRepository<Discount, Long> {

}
