package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface    UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a User by their email address.
     *
     * @param email the email address to search for
     * @return an Optional containing the User if found, otherwise empty
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds all Users by their avatar.
     *
     * @param avatar the avatar to search for
     * @return a List of Users with the specified avatar
     */
    List<User> findByAvatar(String avatar);

    /**
     * Finds a User by their ID.
     *
     * @param id the ID of the user to search for
     * @return an Optional containing the User if found, otherwise empty
     */
    @Override
    Optional<User> findById(Long id);

    /**
     * Finds all Users by their first name.
     *
     * @param firstName the first name to search for
     * @return a List of Users with the specified first name
     */
    List<User> findByFirstName(String firstName);

    /**
     * Finds all Users by their last name.
     *
     * @param lastName the last name to search for
     * @return a List of Users with the specified last name
     */
    List<User> findByLastName(String lastName);

    /**
     * Finds all Users by their birth date.
     *
     * @param birthDate the birth date to search for
     * @return a List of Users with the specified birth date
     */
    List<User> findByBirthDate(LocalDate birthDate);

    /**
     * Finds all Users by their phone number.
     *
     * @param phoneNumber the phone number to search for
     * @return a List of Users with the specified phone number
     */
    List<User> findByPhoneNumber(String phoneNumber);

    /**
     * Finds all Users by their role.
     *
     * @param role the role to search for
     * @return a List of Users with the specified role
     */
    List<User> findByAuthoritiesContaining(String role);

    /**
     * Finds a User by their email address and password.
     *
     * @param email the email address to search for
     * @param password the password to match
     * @return a Set of Users with the specified email and password
     */
    Set<User> findByEmailAndPassword(String email, String password);

    /**
     * Finds a User by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the User if found, otherwise empty
     */
    Optional<User> findByUsername(String username);
}
