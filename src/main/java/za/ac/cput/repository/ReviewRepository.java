package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRating(int rating);

    List<Review> findByProduct_Id(Long product_id);

    List<Review> findByUser_Id(Long user_id);

}
