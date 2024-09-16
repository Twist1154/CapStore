package za.ac.cput.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Orders;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.service.AddressService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AddressControllerTest.java
 *
 * Integration test for AddressController.
 *
 * Author: [Your Name]
 * Date: 10-Sep-24
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AddressService service;

    private final String BASE_URL = "/address";
    private static Address address1;

    @BeforeEach
    void setUp() {
        // Building and creating an address using the AddressFactory
        address1 = AddressFactory.createAddress(
                6L,
                6L,
                "Home",
                "34 Timmy Str",
                "18 northridge",
                "Sun City",
                "South Africa",
                "2309",
                90564424,
                LocalDate.now(),
                LocalDate.of(2024, 6, 24)
        );
        address1 = service.create(address1);
    }

    @AfterEach
    void tearDown() {
        // Deleting the created address after each test
        if (address1 != null && address1.getId() != null) {
            service.delete(address1.getId());
        }
    }

    @Test
    void createAddress() {
        // Creating a new address using the API
        Address address2 = AddressFactory.createAddress(
                7L,
                7L,
                "Home",
                "45 Park Lane",
                "Park verk",
                "Johannesburg",
                "South Africa",
                "3100",
                12345678,
                LocalDate.now(),
                LocalDate.of(2024, 7, 30)
        );

        // Sending a POST request to create the address
        ResponseEntity<Address> response = restTemplate.postForEntity(BASE_URL + "/create", address2, Address.class);

        // Asserting that the response status is CREATED
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // Asserting that the created address is not null
        assertNotNull(response.getBody());
    }

    @Test
    void read() {
        // Reading an address by its ID using the API
        ResponseEntity<Address> response = restTemplate.getForEntity(BASE_URL + "/read/" + address1.getId(), Address.class);

        // Asserting that the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Asserting that the returned address is not null
        assertNotNull(response.getBody());
        assertEquals(address1.getId(), response.getBody().getId());
    }

    @Test
    void updateAddress() {
        // Updating the street of an existing address

        Address addressupdated = new Address.Builder()
                .copy(address1).setAddressLine2("34 freedom Str")
                .build();
        restTemplate.put(BASE_URL + "/update/" + addressupdated.getId(), addressupdated);

        // Reading the updated address
        ResponseEntity<Address> response = restTemplate.getForEntity(BASE_URL + "/read/" + addressupdated.getId(), Address.class);

        // Asserting that the street has been updated
        assertEquals("34 freedom Str", response.getBody().getAddressLine2());
    }

    @Test
    void deleteAddress() {
        // Deleting an existing address using the API
        restTemplate.delete(BASE_URL + "/delete/" + address1.getId());

        // Trying to read the deleted address
        ResponseEntity<Address> response = restTemplate.getForEntity(BASE_URL + "/read/" + address1.getId(), Address.class);

        // Asserting that the address is no longer found
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllAddresses() {
        // Retrieving all addresses using the API
        ResponseEntity<List> response = restTemplate.getForEntity(BASE_URL + "/all", List.class);

        // Asserting that the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Asserting that the returned list is not empty
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void getAddressByUserID() {
        // Reading an address by its userID using the API
        ResponseEntity<Address> response = restTemplate.getForEntity(BASE_URL + "/user/" + address1.getUserId(), Address.class);

        // Asserting that the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Asserting that the address is correctly returned for the given userID
        assertEquals(address1.getUserId(), response.getBody().getUserId());
    }

    @Test
    void getAddressByLine() {
        // Reading an address by its address line
        ResponseEntity<List> response = restTemplate.getForEntity(BASE_URL + "/line/" + address1.getAddressLine1(), List.class);

        // Asserting that the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Asserting that the returned list is not empty
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void getAddressByCountry() {
        // Reading addresses by country using the API
        ResponseEntity<List> response = restTemplate.getForEntity(BASE_URL + "/country/" + address1.getCountry(), List.class);

        // Asserting that the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Asserting that the returned list contains addresses from the correct country
        assertFalse(response.getBody().isEmpty());
    }
}
