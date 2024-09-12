package za.ac.cput.service;

import za.ac.cput.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * IUserService defines the contract for user-related operations.
 * It extends the generic IService interface, providing specific methods
 * for managing {@link User} entities. The service includes functionality for
 * CRUD operations and custom queries such as finding users by email, name, birthdate, and more.
 */
public interface IUserService extends IService<User, Long> {

    List<User> getAll();

    /**
     * Deletes a user by their unique ID.
     *
     * @param id the unique ID of the user to be deleted
     */
    void delete(Long id);

    /**
     * Retrieves a list of all users.
     *
     * @return a list of all users
     */
    List<User> findAll();

    /**
     * Finds a list of users by their avatar image URL or path.
     *
     * @param avatar the avatar image URL or path to search for
     * @return a list of users that match the given avatar
     */
    List<User> findByAvatar(String avatar);

    /**
     * Finds an optional user by their email address.
     *
     * @param email the email address to search for
     * @return an Optional containing the user if found, or an empty Optional if not found
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds a list of users by their first name.
     *
     * @param firstName the first name to search for
     * @return a list of users that match the given first name
     */
    List<User> findByFirstName(String firstName);

    /**
     * Finds a list of users by their last name.
     *
     * @param lastName the last name to search for
     * @return a list of users that match the given last name
     */
    List<User> findByLastName(String lastName);

    /**
     * Finds a list of users by their birth date.
     *
     * @param birthDate the birth date to search for
     * @return a list of users that match the given birth date
     */
    List<User> findByBirthDate(LocalDate birthDate);

    /**
     * Finds a list of users by their phone number.
     *
     * @param phoneNumber the phone number to search for
     * @return a list of users that match the given phone number
     */
    List<User> findByPhoneNumber(Integer phoneNumber);

    /**
     * Finds a list of users by their assigned role (e.g., "Admin", "User").
     *
     * @param role the role to search for
     * @return a list of users that match the given role
     */
    List<User> findByRole(String role);
}