package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a user in the system.
 * <p>
 * This entity class is mapped to the "users" table in the database.
 *
 * @author Rethabile Ntsekhe
 * @date 25-Aug-24
 */

@Entity
@Getter
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username ")
    private String username;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    // ElementCollection with a separate table "user_roles" to store roles
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @JsonIgnore
    private Set<String> role = new HashSet<>();

    public Users() {
    }

    // Private constructor to be used by the builder
    private Users(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.avatar = builder.avatar;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.birthDate = builder.birthDate;
        this.password = builder.password;
        this.phoneNumber = builder.phoneNumber;
        this.role.addAll(builder.role);
    }

    @Override
    public String toString() {
        return "\n User{" +
                "id=" + id +
                ", Username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                "}\n ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) &&
                Objects.equals(avatar, users.avatar) &&
                Objects.equals(username, users.username) &&
                Objects.equals(firstName, users.firstName) &&
                Objects.equals(lastName, users.lastName) &&
                Objects.equals(email, users.email) &&
                Objects.equals(birthDate, users.birthDate) &&
                Objects.equals(password, users.password) &&
                Objects.equals(phoneNumber, users.phoneNumber) &&
                Objects.equals(role, users.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatar,username, firstName, lastName, email, birthDate, password, phoneNumber, role);
    }

    public static class Builder {
        private Long id;
        private String username;
        private String avatar;
        private String firstName;
        private String lastName;
        private String email;
        private LocalDate birthDate;
        private String password;
        private String phoneNumber;
        private Set<String> role = new HashSet<>();

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setPhoneNumber(String  phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setRole(Set<String> role) {
            this.role = role;
            return this;
        }

        public Builder copy(Users users) {
            this.id = users.getId();
            this.username = users.getUsername();
            this.avatar = users.getAvatar();
            this.firstName = users.getFirstName();
            this.lastName = users.getLastName();
            this.email = users.getEmail();
            this.birthDate = users.getBirthDate();
            this.password = users.getPassword();
            this.phoneNumber = users.getPhoneNumber();
            this.role = new HashSet<>(users.getRole());
            return this;
        }

        public Users build() {
            return new Users(this);
        }
    }
}
