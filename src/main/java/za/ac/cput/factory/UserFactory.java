package za.ac.cput.factory;

import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Set;

public class UserFactory {

    public static User createUser(String avatar, String firstName, String lastName, String email,
                                  LocalDate birthDate, Set<String> role, Integer phoneNumber, String password){

        if (Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(lastName) ||
                Helper.isNullOrEmpty(email) ||
                Helper.isNullOrEmpty(password)) {
            throw new IllegalArgumentException("First name, last name, email, and password cannot be null or empty");
        }
        // Create a new User object using the Builder pattern
        return new User.Builder()
                .setAvatar(avatar)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setBirthDate(birthDate)
                .setRole(role) // Set the roles as a Set<String>
                .setPhoneNumber(phoneNumber)
                .setPassword(password)
                .build();
    }
    public static User createUserForSignIn(String email, String password) {
        // Validation checks
        if (Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(password)) {
            throw new IllegalArgumentException("Email and password cannot be null or empty");
        }

        return new User.Builder()
                .setEmail(email)
                .setPassword(password)
                .build();
    }
}
