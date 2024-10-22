package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Users;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);

    private UserFactory userFactory;

    /**
     * Constructs a UserService with the specified {@link UserRepository} and {@link PasswordEncoder}.
     *
     * @param userRepository  the repository for interacting with the User entity in the database
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new users in the database.
     *
     * @param users the users to be created
     * @return the created users
     */
    @Override
    public Users create(Users users) {
        Users encrptedPasword= new Users.Builder()
                .copy(users)
                .setId(users.getId())
                .setFirstName(users.getFirstName())
                .setLastName(users.getLastName())
                .setEmail(users.getEmail())
                .setPassword(encoder.encode(users.getPassword()) )
                .setRole(users.getRole())
                .setBirthDate(users.getBirthDate())
                .setPhoneNumber(users.getPhoneNumber())
                .build();
        return userRepository.save(encrptedPasword);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return the user, or {@code null} if no user is found
     */
    @Override
    public Users read(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing users in the database.
     *
     * @param users the users with updated details
     * @return the updated users, or {@code null} if the users does not exist
     */
    @Override
    public Users update(Users users) {
         Users existing = userRepository.findById(users.getId()).orElse(null);
        if (existing != null) {
            Users updatedusers= new Users.Builder()
                    .copy(existing)
                    .setId(existing.getId())
                    .setFirstName(users.getFirstName())
                    .setLastName(users.getLastName())
                    .setEmail(users.getEmail())
                    .setPassword(users.getPassword())
                    .setRole(users.getRole())
                    .setBirthDate(users.getBirthDate())
                    .setPhoneNumber(users.getPhoneNumber())
                    .build();
            return userRepository.save(updatedusers);
        }
        return null;
    }


    /**
     * Retrieves all users in the system.
     *
     * @return a list of all users
     */
    @Override
    public List<Users> getAll() {
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
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Users> findByAvatar(String avatar) {
        return userRepository.findByAvatar(avatar);
    }



    /**
     * Finds a user by their email.
     *
     * @param email the email of the user
     * @return an {@link Optional} containing the user, or empty if not found
     */
    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Finds users by their first name.
     *
     * @param firstname the first name of the users
     * @return a list of users with the specified first name
     */
    @Override
    public List<Users> findByFirstName(String firstname) {
        return userRepository.findByFirstName(firstname);
    }

    /**
     * Finds users by their last name.
     *
     * @param lastName the last name of the users
     * @return a list of users with the specified last name
     */
    @Override
    public List<Users> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    /**
     * Finds users by their birth date.
     *
     * @param birthDate the birth date of the users
     * @return a list of users with the specified birth date
     */
    @Override
    public List<Users> findByBirthDate(LocalDate birthDate) {
        return userRepository.findByBirthDate(birthDate);
    }

    /**
     * Finds users by their phone number.
     *
     * @param phoneNumber the phone number of the users
     * @return a list of users with the specified phone number
     */
    @Override
    public List<Users> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    /**
     * Finds users by their role.
     *
     * @param role the role of the users
     * @return a list of users with the specified role
     */
    @Override
    public List<Users> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    /*** Verifies a user's credentials.** @param email the email address* @param password the password* @return the User if credentials are valid, otherwise null*/
    public Set<Users> verifyUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}