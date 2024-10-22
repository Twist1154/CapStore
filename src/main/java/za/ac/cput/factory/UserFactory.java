package za.ac.cput.factory;

import za.ac.cput.domain.Users;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Set;

public class UserFactory {

    public static Users createUser(String avatar,
                                   String username,
                                   String firstName,
                                   String lastName,
                                   String email,
                                   LocalDate birthDate,
                                   Set<String> role,
                                   String phoneNumber,
                                   String password) {

        if (Helper.isNullOrEmpty(username) ||
                Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(lastName) ||
                Helper.isNullOrEmpty(lastName) ||
                Helper.isNullOrEmpty(email) ||
                Helper.isNullOrEmpty(password)) {
            throw new IllegalArgumentException("First name, last name, email, and password cannot be null or empty");
        }
        return new Users.Builder()
                .setUsername(username)
                .setAvatar(avatar)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setBirthDate(birthDate)
                .setRole(role)
                .setPhoneNumber(phoneNumber)
                .setPassword(password)
                .build();
    }

    public static Users createUserForSignIn(String email, String password) {
        // Validation checks
        if (Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(password)) {
            throw new IllegalArgumentException("Email and password cannot be null or empty");
        }

        return new Users.Builder()
                .setEmail(email)
                .setPassword(password)
                .build();
    }
}
