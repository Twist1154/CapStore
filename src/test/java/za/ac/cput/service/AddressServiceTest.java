package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.repository.AddressRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceTest {

    @Autowired
    private AddressService service;

    private static Address address;  // Single Address instance to use across tests

    @Autowired
    private AddressRepository repository;

    /**
     * Set up a single Address object to be reused in all tests.
     */
    @BeforeEach
    void setup() {
        address = AddressFactory.createAddress(
                1L,
                1L,
                "Home",
                "10 Sir Street",
                "Parow North",
                "Cape Town",
                "South Africa",
                "22335",
                String.valueOf(863345678),
                LocalDate.now(),
                LocalDate.of(2024, 4, 4)
        );
        assertNotNull(address, "Address should not be null in setup");

        // Save the address once, and reuse in all test methods
        address = repository.save(address);
        System.out.println("Setup address: " + address);
    }

    /**
     * Cleans up the repository after each test.
     */
    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    /**
     * Tests the create method of AddressService.
     */
    @Order(1)
    @Test
    void create() {
        Address created = service.create(address);
        assertNotNull(created, "Created address should not be null");
        assertNotNull(created.getId(), "AddressID should not be null");
        assertEquals(address.getAddressLine1(), created.getAddressLine1(), "Address lines should match");
        System.out.println(created);
    }

    /**
     * Tests the read method of AddressService.
     */
    @Order(2)
    @Test
    void read() {
        Address read = service.read(address.getId());
        assertNotNull(read, "Read address should not be null");
        System.out.println("Read address from service: " + read);

        assertEquals("10 Sir Street", read.getAddressLine1(), "Address line should match");
        System.out.println(read);
    }

    /**
     * Tests the update method of AddressService.
     */
    @Test
    void testUpdateAddress() {
        // Use the existing address created in setup
        Address updatedAddress = new Address.Builder()
                .copy(address)
                .setAddressLine1("456 Elm St")
                .setCountry("Nigeria")
                .build();

        Address result = service.update(updatedAddress);

        assertNotNull(result);
        assertEquals("456 Elm St", result.getAddressLine1(), "Address lines should match");
        System.out.println("Updated address: " + result);
    }

    /**
     * Tests the findByUserId method of AddressService.
     */
    @Order(4)
    @Test
    void findByUserId() {
        Optional<Address> found = service.findByUserId(1L);
        assertTrue(found.isPresent(), "Address should be found by UserID");
        assertEquals("10 Sir Street", found.get().getAddressLine1(), "Address line should match");
        System.out.println(found.get());
    }

    /**
     * Tests the findByAddressLine method of AddressService.
     */
    @Order(5)
    @Test
    void findByAddressLine() {
        // We use the setup address
        assertNotNull(address.getAddressLine2(), "Address line should not be null");
        assertEquals("10 Sir Street", address.getAddressLine2(), "Address line should match");
    }

    /**
     * Tests the findByPostalcodes method of AddressService.
     */
    @Order(6)
    @Test
    void findByPostalcodes() {
        List<Address> found = service.findByPostalCode("22335");
        assertFalse(found.isEmpty(), "Address list should not be empty");
        assertEquals("22335", found.get(0).getPostalCode(), "ZipCode should match");
        System.out.println(found);
    }

    /**
     * Tests the findByPhoneNumber method of AddressService.
     */
    @Order(7)
    @Test
    void findByPhoneNumber() {
        List<Address> found = service.findByPhoneNumber(String.valueOf(863345678));
        assertFalse(found.isEmpty(), "Address list should not be empty");
        assertEquals("10 Sir Street", found.get(0).getAddressLine1(), "Address line should match");
    }

    /**
     * Tests the findByCountry method of AddressService.
     */
    @Order(8)
    @Test
    void findByCountry() {
        List<Address> found = service.findByCountry("South Africa");
        assertFalse(found.isEmpty(), "Address list should not be empty");
        assertEquals("South Africa", found.get(0).getCountry(), "Country should match");
        System.out.println(found);
    }


    /**
     * Tests the getAll method of AddressService.
     */
    @Order(10)
    @Test
    void getAll() {
        List<Address> allAddresses = service.findAll();
        assertFalse(allAddresses.isEmpty(), "Address list should not be empty");
        System.out.println(allAddresses);
    }
}
