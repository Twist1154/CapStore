package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "users")
public class User implements UserDetails {


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

    @Column(nullable = false)
    private boolean enabled = true;

   // @ElementCollection(fetch = FetchType.EAGER)
    //@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
    //@ElementCollection(fetch = FetchType.EAGER)
    //@CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    //@Column(name = "authority")
    //private Set<String> authorities = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference("userAddressReference")
    private List<Address> address = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    @JsonBackReference("userReviewReference")
    @JsonIgnore
    private List<Review> review = new ArrayList<>();

    public User() {
    }

    // Private constructor to be used by the builder
    private User(Builder builder) {
        this.id = builder.id;
        this.avatar = builder.avatar;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.username = builder.username;
        this.email = builder.email;
        this.birthDate = builder.birthDate;
        this.password = builder.password;
        this.phoneNumber = builder.phoneNumber;
        this.role = builder.role;
        this.address = builder.address != null ? builder.address : new ArrayList<>();
        this.review = builder.review != null ? builder.review : new ArrayList<>();

    }

    // Getters


    public Long getId() {
        return id;
    }


    public String getAvatar() {
        return avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "\n User{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", ADDRESS: " + address +
                ", REVIEW: " + review +
                ", enabled=" + enabled +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(avatar, user.avatar) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatar, firstName, lastName, email, birthDate, password, phoneNumber, role);
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
        private Role role;

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

        public Builder setUsername(String username) {
            this.username = username;
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


        public Builder setRole(Role role) {
            this.role = role;
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
            this.role =user.getRole();
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
