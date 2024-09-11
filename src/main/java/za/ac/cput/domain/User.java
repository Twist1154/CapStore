package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a User entity in the system.
 * It is an entity class that maps to the "User" table in the database.
 */
@Entity
@Table(name = "Users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String avatar;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    private String password;
    private Integer phoneNumber;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> role = new HashSet<>();
    public User() {}
    private User(Builder builder) {
        this.userID = builder.userID;
        this.avatar = builder.avatar;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.birthDate = builder.birthDate;
        this.password = builder.password;
        this.phoneNumber = builder.phoneNumber;
        this.role.addAll(builder.role);
    }

    // Getters

    /**
     * Gets the user ID.
     * @return the user ID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Gets the avatar URL or path.
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Gets the first name of the user.
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the user.
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the email address of the user.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the birth date of the user.
     * @return the birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Gets the password of the user.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the phone number of the user.
     * @return the phone number
     */
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the roles assigned to the user.
     * @return a set of roles
     */
    public Set<String> getRole() {
        return role;
    }

    /**
     * Generates a string representation of the User object.
     * @return a string representation of the User
     */
    @Override
    public String toString() {
        return "'\n'+User{" +
                "id=" + userID +
                ", avatar='" + avatar + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '\n';
    }

    /**
     * Compares this User with another object.
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userID, user.userID) &&
                Objects.equals(avatar, user.avatar) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(role, user.role);
    }

    /**
     * Generates a hash code for the User object.
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(userID, avatar, firstName, lastName, email, birthDate, password, phoneNumber, role);
    }

    /**
     * Builder class for constructing User instances using the builder pattern.
     */
    public static class Builder {
        private Long userID;
        private String avatar;
        private String firstName;
        private String lastName;
        private String email;
        private LocalDate birthDate;
        private String password;
        private Integer phoneNumber;
        private Set<String> role = new HashSet<>();

        /**
         * Sets the user ID.
         * @param userID the user ID to set
         * @return the current Builder instance
         */
        public Builder setId(Long userID) {
            this.userID = userID;
            return this;
        }

        /**
         * Sets the avatar URL or path.
         * @param avatar the avatar to set
         * @return the current Builder instance
         */
        public Builder setAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        /**
         * Sets the first name.
         * @param firstName the first name to set
         * @return the current Builder instance
         */
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Sets the last name.
         * @param lastName the last name to set
         * @return the current Builder instance
         */
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Sets the email.
         * @param email the email to set
         * @return the current Builder instance
         */
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the birth date.
         * @param birthDate the birth date to set
         * @return the current Builder instance
         */
        public Builder setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        /**
         * Sets the password.
         * @param password the password to set
         * @return the current Builder instance
         */
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Sets the phone number.
         * @param phoneNumber the phone number to set
         * @return the current Builder instance
         */
        public Builder setPhoneNumber(Integer phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * Sets the roles assigned to the user.
         * @param role the roles to set
         * @return the current Builder instance
         */
        public Builder setRole(Set<String> role) {
            this.role = role;
            return this;
        }

        /**
         * Copies an existing User object into the builder.
         * @param user the User object to copy
         * @return the current Builder instance
         */
        public Builder copy(User user) {
            this.userID = user.getUserID();
            this.avatar = user.getAvatar();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.email = user.getEmail();
            this.birthDate = user.getBirthDate();
            this.password = user.getPassword();
            this.phoneNumber = user.getPhoneNumber();
            this.role = new HashSet<>(user.getRole());
            return this;
        }

        /**
         * Builds and returns a new User instance based on the builder's configuration.
         * @return the newly created User object
         */
        public User build() {
            return new User(this);
        }
    }
}
