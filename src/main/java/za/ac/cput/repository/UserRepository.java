package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Users;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<Users, Long > {
    Optional<Users> findByEmail(String email);
    List<Users> findByAvatar(String avatar);
    List<Users> findByFirstName(String firstName);
    List<Users> findByLastName(String lastname);
    List<Users> findByBirthDate(LocalDate birthDate);
    List<Users> findByPhoneNumber(String phoneNumber);
    List<Users> findByRole(String role);
    Set<Users> findByEmailAndPassword(String email, String password);
    Optional<Users> findByUsername(String username);
}


