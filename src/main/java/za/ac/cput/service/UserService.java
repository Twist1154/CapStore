package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService, IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a UserService with the specified {@link UserRepository} and {@link PasswordEncoder}.
     *
     * @param userRepository  the repository for interacting with the User entity in the database
     * @param passwordEncoder the encoder for encoding user passwords
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new user in the database.
     *
     * @param user the user to be created
     * @return the created user
     */
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return the user, or {@code null} if no user is found
     */
    @Override
    public User read(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing user in the database.
     *
     * @param user the user with updated details
     * @return the updated user, or {@code null} if the user does not exist
     */
    @Override
    public User update(User user) {
        if (userRepository.existsById(user.getUserID())) {
            return userRepository.save(user);
        }
        return null;
    }

    /**
     * Retrieves all users in the system.
     *
     * @return a list of all users
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll(); // This can be updated to return actual data if needed
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to be deleted
     */
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Finds all users in the system.
     *
     * @return a list of all users
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByAvatar(String avatar) {
        return userRepository.findByAvatar(avatar);
    }

    /**
     * Loads user-specific data by username (usually email for authentication).
     *
     * @param username the username (or email) of the user
     * @return the {@link UserDetails} object containing user data
     * @throws UsernameNotFoundException if no user is found with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        // Convert roles to SimpleGrantedAuthority
        List<SimpleGrantedAuthority> authorities = user.getRole().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    /**
     * Finds a user by their email.
     *
     * @param email the email of the user
     * @return an {@link Optional} containing the user, or empty if not found
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Finds users by their first name.
     *
     * @param firstname the first name of the users
     * @return a list of users with the specified first name
     */
    @Override
    public List<User> findByFirstName(String firstname) {
        return userRepository.findByFirstName(firstname);
    }

    /**
     * Finds users by their last name.
     *
     * @param lastName the last name of the users
     * @return a list of users with the specified last name
     */
    @Override
    public List<User> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    /**
     * Finds users by their birth date.
     *
     * @param birthDate the birth date of the users
     * @return a list of users with the specified birth date
     */
    @Override
    public List<User> findByBirthDate(LocalDate birthDate) {
        return userRepository.findByBirthDate(birthDate);
    }

    /**
     * Finds users by their phone number.
     *
     * @param phoneNumber the phone number of the users
     * @return a list of users with the specified phone number
     */
    @Override
    public List<User> findByPhoneNumber(Integer phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    /**
     * Finds users by their role.
     *
     * @param role the role of the users
     * @return a list of users with the specified role
     */
    @Override
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }
}