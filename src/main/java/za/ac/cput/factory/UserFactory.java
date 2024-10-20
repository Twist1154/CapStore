package za.ac.cput.factory;

import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Set;

public class UserFactory {

    public static User createUser(String avatar,
                                  String firstName,
                                  String lastName,
                                  String username,
                                  String email,
                                  LocalDate birthDate,
                                  Set<String> authorities,
                                  String phoneNumber,
                                  String password) {

        if (Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(lastName) ||
                Helper.isNullOrEmpty(username) ||
                Helper.isNullOrEmpty(email) ||
                Helper.isNullOrEmpty(password)) {
            throw new IllegalArgumentException("First name, last name, username, email, and password cannot be null or empty");
        }

        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }

        if (Helper.isNullOrEmpty(phoneNumber)) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

        // Create a new User object using the Builder pattern
        return new User.Builder()
                .setAvatar(avatar)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUsername(username)
                .setEmail(email)
                .setBirthDate(birthDate)
                .setAuthorities(authorities)  // Set authorities (roles)
                .setPhoneNumber(phoneNumber)
                .setPassword(password)
                .build();
    }

    public static User createUserForSignIn(String username, String password) {
        // Validation checks
        if (Helper.isNullOrEmpty(username) || Helper.isNullOrEmpty(password)) {
            throw new IllegalArgumentException("Email and password cannot be null or empty");
        }

        return new User.Builder()
                .setUsername(username)
                .setPassword(password)
                .build();
    }
}
