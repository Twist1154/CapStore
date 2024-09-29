package za.ac.cput.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Users;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<Users, Long > {
    /*** Finds a User by their email address.** @param email the email address to search for* @return an Optional containing the User if found, otherwise empty*/
    Optional<Users> findByEmail(String email);

    List<Users> findByAvatar(String avatar);
    /*** Finds a User by their username.** @param username the username to search for* @return an Optional containing the User if found, otherwise empty*/
    @Override
    Optional<Users> findById(Long id);

    /*** Finds all Users by their first name.** @param firstName the first name to search for* @return a List of Users with the specified first name*/
    List<Users> findByFirstName(String firstName);

    /*** Finds all Users by their last name.** @param lastName the last name to search for* @return a List of Users with the specified last name*/
    List<Users> findByLastName(String lastname);

    /*** Finds all Users by their birth date.** @param birthDate the birth date to search for* @return a List of Users with the specified birth date*/
    List<Users> findByBirthDate(LocalDate birthDate);

    /*** Finds all Users by their phone number.** @param phoneNumber the phone number to search for* @return a List of Users with the specified phone number*/
    List<Users> findByPhoneNumber(Integer phoneNumber);

    /*** Finds all Users by their role.** @param role the role to search for* @return a List of Users with the specified role*/
    List<Users> findByRole(String role);

    /*** Finds a User by their email address and password.** @param email the email address to search for* @param password the password to match* @return an Optional containing the User if found, otherwise empty*/
    Set<Users> findByEmailAndPassword(String email, String password);

}


