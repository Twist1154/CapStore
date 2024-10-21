package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
    Optional<User> findByEmail(String email);

    List<User> findByAvatar(String avatar);

    @Override
    Optional<User> findById(Long id);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastname);

    List<User> findByBirthDate(LocalDate birthDate);

    List<User> findByPhoneNumber(String phoneNumber);

    List<User> findByRole(String role);

}


