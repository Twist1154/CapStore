package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Address;
import za.ac.cput.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * AddressController.java
 *
 * Controller class to handle HTTP requests for Address.
 *
 */

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*")
public class AddressController {

    private final AddressService addressService;
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Create a new address
    @PostMapping("/create")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        try {
            if (address == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Address newAddress = addressService.create(address);
            return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating address", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read an address by ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> read(@PathVariable Long id) {
        try {
            Address address = addressService.read(id);
            if (address != null) {
                return new ResponseEntity<>(address, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error reading address with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing address
    @PutMapping("/update")
    public ResponseEntity<Address> update(@RequestBody Address address) {
        try {
            if (address == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Address updatedAddress = addressService.update(address);
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating address", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all addresses
    @GetMapping("/all")
    public ResponseEntity<List<Address>> findAll() {
        try {
            List<Address> addresses = addressService.findAll();
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching all addresses", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete an address by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            Address existingAddress = addressService.read(id);
            if (existingAddress == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            addressService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting address with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by user ID
    @GetMapping("/user")
    public ResponseEntity<Optional<Address>> findByUser(@RequestParam Long userId) {
        try {
            Optional<Address> address = addressService.findByUserId(userId);
            if (address.isPresent()) {
                return new ResponseEntity<>(address, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            logger.error("Error fetching address for userId " + userId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by title
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Address>> findByTitle(@PathVariable String title) {
        try {
            List<Address> addresses = addressService.findByTitle(title);
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching addresses by title " + title, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by address line 1
    @GetMapping("/address-line1/{addressLine1}")
    public ResponseEntity<List<Address>> findByAddressLine1(@PathVariable String addressLine1) {
        try {
            List<Address> addresses = addressService.findByAddressLine1(addressLine1);
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching addresses by address line 1 " + addressLine1, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by address line 2
    @GetMapping("/address-line2/{addressLine2}")
    public ResponseEntity<List<Address>> findByAddressLine2(@PathVariable String addressLine2) {
        try {
            List<Address> addresses = addressService.findByAddressLine2(addressLine2);
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching addresses by address line 2 " + addressLine2, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by country
    @GetMapping("/country/{country}")
    public ResponseEntity<List<Address>> findByCountry(@PathVariable String country) {
        try {
            List<Address> addresses = addressService.findByCountry(country);
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching addresses by country " + country, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Address>> findByCity(@PathVariable String city) {
        try {
            List<Address> addresses = addressService.findByCity(city);
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching addresses by city " + city, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by postal code
    @GetMapping("/postal-code/{postalCode}")
    public ResponseEntity<List<Address>> findByPostalCode(@PathVariable String postalCode) {
        try {
            List<Address> addresses = addressService.findByPostalCode(postalCode);
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching addresses by postal code " + postalCode, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by phone number
    @GetMapping("/phone-number/{phoneNumber}")
    public ResponseEntity<List<Address>> findByPhoneNumber(@PathVariable Integer phoneNumber) {
        try {
            List<Address> addresses = addressService.findByPhoneNumber(phoneNumber);
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching addresses by phone number " + phoneNumber, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by created date after
    @GetMapping("/created-after/{createdAt}")
    public ResponseEntity<List<Address>> findByCreatedAtAfter(@PathVariable LocalDate createdAt) {
        try {
            List<Address> addresses = addressService.findByCreatedAtAfter(createdAt);
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching addresses created after " + createdAt, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by updated date
    @GetMapping("/updated-after/{date}")
    public ResponseEntity<List<Address>> findByUpdatedAt(@PathVariable LocalDate date) {
        try {
            List<Address> addresses = addressService.findByUpdatedAt(date);
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching addresses updated after " + date, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
