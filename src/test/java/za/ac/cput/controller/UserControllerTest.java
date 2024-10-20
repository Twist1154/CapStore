package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.User;
import za.ac.cput.service.UserService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@example.com")
                .setPassword("password")
                .setAvatar("default-avatar.png")
                .setPhoneNumber("1234567890")
                .setBirthDate(LocalDate.of(1990, 1, 1))
                .setAuthorities(Collections.singleton("USER"))
                .build();
    }

    @Order(1)
    @Test
    void createUser_success() {
        // Create a new user via the API call
        ResponseEntity<User> response = restTemplate.postForEntity("/users/create", user, User.class);

        // Assert that the response status is OK
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Check if the response body is not null
        assertThat(response.getBody()).isNotNull();

        // Get the User from the response body
        User createdUser = response.getBody();

        // Assert that the User ID is greater than 0
        assertThat(createdUser.getId()).isGreaterThan(0L);
    }

    @Order(2)
    @Test
    void readUser_success() {
        // Make a POST request to create the user
        ResponseEntity<User> createResponse = restTemplate.postForEntity("/user/create", user, User.class);

        // Check that the response status is OK
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Ensure the response body is not null
        assertThat(createResponse.getBody()).isNotNull();

        // Get the user object from the response body
        User createdUser = createResponse.getBody();

        // Perform GET request to read the user
        ResponseEntity<User> readResponse = restTemplate.getForEntity("/user/read/" + createdUser.getUserID(), User.class);

        // Assert the response status and read user
        assertThat(readResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(readResponse.getBody()).isNotNull();
        assertThat(readResponse.getBody().getUserID()).isEqualTo(createdUser.getUserID());
    }

    @Order(3)
    @Test
    void updateUser_success() {
        // Step 1: Create a new user
        ResponseEntity<User> createResponse = restTemplate.postForEntity("/user/create", user, User.class);

        // Assert that the user was created successfully
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        User createdUser = createResponse.getBody();
        assertThat(createdUser).isNotNull();
        Long userID = createdUser.getUserID();

        // Step 2: Prepare the updated user data
        User updatedUser = new User.Builder()
                .setId(userID)
                .setFirstName("Jane")
                .setLastName("Doe")
                .setEmail("jane.doe@example.com")
                .setPassword("newpassword")
                .setAvatar("new-avatar.png")
                .setPhoneNumber(987654321)
                .setBirthDate(LocalDate.of(1992, 5, 10))
                .setAuthorities(Collections.singleton("ADMIN"))
                .build();


        // Step 3: Perform PUT request to update the user
        ResponseEntity<Void> putResponse = restTemplate.exchange("/user/update/{userID}", HttpMethod.PUT, new HttpEntity<>(updatedUser), Void.class, userID);

        // Assert that the PUT request was successful
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Step 4: Perform GET request to verify the updated user
        ResponseEntity<User> updatedResponse = restTemplate.getForEntity("/user/read/" + userID, User.class);
        User updatedResponseBody = updatedResponse.getBody();

        // Assert the response status and updated fields
        assertThat(updatedResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assert updatedResponseBody != null;
        assertThat(updatedResponseBody.getFirstName()).isEqualTo("Jane");
        assertThat(updatedResponseBody.getAuthorities()).isEqualTo("ADMIN");
    }

    @Order(4)
    @Test
    void deleteUser_success() {
        // First, create a new user
        ResponseEntity<User> createResponse = restTemplate.postForEntity("/user/create", user, User.class);

        // Assert that the user was created successfully
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        User createdUser = createResponse.getBody();
        assertThat(createdUser).isNotNull();

        // Then, delete the created user
        Long userId = createdUser.getUserID();
        restTemplate.delete("/user/delete/" + userId);

        // Try to retrieve the deleted user (should return 404)
        ResponseEntity<Void> getResponse = restTemplate.getForEntity("/user/read/" + userId, Void.class);

        // Assert that retrieving the deleted user returns 404 Not Found
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Order(5)
    @Test
    void findAllUsers_success() {
        ResponseEntity<List> response = restTemplate.getForEntity("/user/all", List.class);

        // Assert that the response status is OK
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Assert that the response body is not null
        assertThat(response.getBody()).isNotNull();
    }
}