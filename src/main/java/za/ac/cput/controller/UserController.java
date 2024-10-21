package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.AuthenticationResponse;
import za.ac.cput.domain.User;
import za.ac.cput.service.AuthenticationService;
import za.ac.cput.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Controller for managing users in the system.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final AuthenticationService authService;
    private final UserService userService;



    public UserController(AuthenticationService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user if found, or 404 status if not
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/read/{id}")
    public ResponseEntity<User> readUser(@PathVariable Long id) {
        User user = userService.read(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        System.out.println("user deleted");
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        System.out.println("Getting all users");
        List<User> users = userService.findAll();
        System.out.println("Retrieved " + users.size() + " users");
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<User>> findByFirstName(@PathVariable String firstName) {
        List<User> users = userService.findByFirstName(firstName);
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<User>> findByLastName(@PathVariable String lastName) {
        List<User> users = userService.findByLastName(lastName);
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/birthdate/{birthDate}")
    public ResponseEntity<List<User>> findByBirthDate(@PathVariable LocalDate birthDate) {
        List<User> users = userService.findByBirthDate(birthDate);
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<List<User>> findByPhoneNumber(@PathVariable String phoneNumber) {
        List<User> users = userService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> findByRole(@PathVariable String role) {
        List<User> users = userService.findByRole(role);
        return ResponseEntity.ok(users);
    }


}
