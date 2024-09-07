package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Cart;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {
    // Additional query methods can be defined here if needed
}