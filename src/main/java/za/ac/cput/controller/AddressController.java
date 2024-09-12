package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Address;
import za.ac.cput.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * AddressController.java
 *
 * Controller class to handle HTTP requests for Address.
 *
 * Author: [Your Name]
 * Date: 10-Sep-24
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/address")
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
    @GetMapping("/read/{id}")
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
    @PutMapping("/update/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        try {
            if (address == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Address existingAddress = addressService.read(id);
            if (existingAddress == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Address updatedAddress = addressService.update(address);
            return ResponseEntity.ok(updatedAddress);
        } catch (Exception e) {
            logger.error("Error updating address with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete an address by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        try {
            Address existingAddress = addressService.read(id);
            if (existingAddress == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            addressService.deleteByAddressID(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting address with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all addresses
    @GetMapping("/all")
    public ResponseEntity<List<Address>> getAllAddresses() {
        try {
            List<Address> addresses = addressService.getAll();
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            logger.error("Error fetching all addresses", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by userID
    @GetMapping("/user/{userID}")
    public ResponseEntity<Address> getAddressByUserID(@PathVariable Long userID) {
        try {
            Address address = addressService.findByUserID(userID).orElse(null);
            if (address == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching address for userID " + userID, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by address line
    @GetMapping("/line/{addressLine}")
    public ResponseEntity<List<Address>> getAddressByLine(@PathVariable String addressLine) {
        try {
            List<Address> addresses = addressService.findByAddressLine(addressLine);
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            logger.error("Error fetching address with line " + addressLine, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get address by country
    @GetMapping("/country/{country}")
    public ResponseEntity<List<Address>> getAddressByCountry(@PathVariable String country) {
        try {
            List<Address> addresses = addressService.findByCountry(country);
            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            logger.error("Error fetching address with country " + country, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
