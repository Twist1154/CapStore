package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.UserPrincipal;
import za.ac.cput.domain.Users;
import za.ac.cput.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Users user = usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // Log the user's roles
        System.out.println("Loaded user: " + user.getEmail() + " with roles: " + user.getRole());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRole().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }
    

    public UserDetails loadUserById(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrincipal(user);
    }

}
