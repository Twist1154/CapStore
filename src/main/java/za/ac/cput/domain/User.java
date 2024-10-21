package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "authority")
    private Set<String> authorities = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference("userAddressReference")
    private List<Address> address = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    @JsonBackReference("userReviewReference")
    @JsonIgnore
    private List<Review> review = new ArrayList<>();

    public User() {
        // Default constructor
    }

    // Private constructor to be used by the builder
    private User(Builder builder) {
        this.id = builder.id;
        this.avatar = builder.avatar;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.birthDate = builder.birthDate;
        this.username = builder.username;
        this.password = builder.password;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address != null ? builder.address : new ArrayList<>();
        this.review = builder.review != null ? builder.review : new ArrayList<>();
        this.authorities = new HashSet<>(builder.authorities);  // Ensure non-null authorities set
    }

    @Override
    public String toString() {
        return "\n User{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", UserName ='" + username + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", ADDRESS: " + address +
                ", REVIEW: " + review +
                ", role=" + authorities +
                "}\n ";

    }



    public static class Builder {
        private Long id;
        private String avatar;
        private String firstName;
        private String lastName;
        private String username;
        private String email;
        private LocalDate birthDate;
        private String password;
        private String phoneNumber;
        private List<Address> address;
        private List<Review> review;
        private Set<String> authorities = new HashSet<>();

        public Builder setId(Long id) {
            this.id = id;
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

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setAddress(List<Address> address) {
            this.address = address;
            return this;
        }

        public Builder setReview(List<Review> review) {
            this.review = review;
            return this;
        }

        public Builder setAuthorities(Set<String> authorities) {
            this.authorities = authorities;
            return this;
        }

        public Builder copy(User user) {
            this.id = user.getId();
            this.avatar = user.getAvatar();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.email = user.getEmail();
            this.birthDate = user.getBirthDate();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.phoneNumber = user.getPhoneNumber();
            this.authorities = new HashSet<>(user.getAuthorities());
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
