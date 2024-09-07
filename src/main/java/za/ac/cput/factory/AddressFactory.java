package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;
import java.time.LocalDate;

/**
 * Factory class for Address domain entity.
 * Provides methods for creating Address objects with input validation.
 */
public class AddressFactory {

    /**
     * Creates and returns a new Address object with the given parameters.
     *
     * @param addressId   the ID of the address (nullable for new addresses)
     * @param userId      the ID of the user associated with the address (required)
     * @param addressLine the address line (required)
     * @param city        the city (required)
     * @param country     the country (required)
     * @param zipCode     the zip code (required)
     * @param phoneNumber the phone number (required)
     * @param createdAt   the creation date (required)
     * @param deletedAt   the deletion date (nullable)
     * @return a new Address object
     * @throws IllegalArgumentException if required fields are null or empty
     */
    public static Address buildAddress(Long addressId,
                                       Long userId,
                                       String addressLine,
                                       String city,
                                       String country,
                                       String zipCode,
                                       Integer phoneNumber,
                                       LocalDate createdAt,
                                       LocalDate deletedAt) {

        // Validate required fields
        if (Helper.isNullOrEmpty(userId) ||
                Helper.isNullOrEmpty(addressLine) ||
                Helper.isNullOrEmpty(city) ||
                Helper.isNullOrEmpty(country) ||
                Helper.isNullOrEmpty(zipCode) ||
                phoneNumber == null ||
                createdAt == null) {
            throw new IllegalArgumentException("Required fields cannot be null or empty");
        }

        // Build and return the Address object
        return new Address.Builder()
                .setAddressID(addressId)
                .setUserID(userId)
                .setAddressLine(addressLine)
                .setCity(city)
                .setCountry(country)
                .setZipCode(zipCode)
                .setPhoneNumber(phoneNumber)
                .setCreatedAt(createdAt)
                .setDeletedAt(deletedAt)  // Optional: can be null
                .build();
    }
}
