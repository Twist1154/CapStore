package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.UserPrincipal;
import za.ac.cput.domain.Users;
import za.ac.cput.repository.UserRepository;

import java.util.Set;

/**
 * MyUserDetailsService.java
 *
 * This service provides user-specific data required for authentication.
 *
 * @author Rethabile
 * @date 29-Sep-24
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository usersRepository;

    /**
     * Loads user-specific data by username (usually email for authentication).
     *
     * @param username the username (or email) of the user
     * @return the {@link UserDetails} object containing user data
     * @throws UsernameNotFoundException if no user is found with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username + "from myUserDetailsService loadUserByUsername");
        Users users = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return new UserPrincipal(users);
    }

    /**
     * Verifies a user's credentials.
     *
     * @param email the email address
     * @param password the password
     * @return the set of Users if credentials are valid, otherwise null
     */
    public Set<Users> verifyUser(String email, String password) {
        System.out.println("email: " + email + " password: " + password + "from myUserDetailsService verifyUser");
        return usersRepository.findByEmailAndPassword(email, password);
    }
}
