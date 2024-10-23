package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.Users;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

/**
 * Factory class for creating instances of {@link Address}.
 * Provides static methods to create {@link Address} objects from various inputs.
 *
 * @author Rethabile Ntsekhe
 * @date 24-Aug-24
 */
public class AddressFactory {

    /**
     * Creates a {@link Address} instance from a {@link Address}.
     *
     * @param id         the ID of the address
     * @param users       the users associated with the address
     * @param title      the title of the address
     * @param addressLine1 the first line of the address
     * @param addressLine2 the second line of the address
     * @param city       the city of the address
     * @param country    the country of the address
     * @param postalCode the postal code of the address
     * @param phoneNumber the phone number associated with the address
     * @param createdAt  the date the address was created
     * @param UpdatedAt  the date the address was deleted (if applicable)
     * @return a new {@link Address} object with properties set from the input parameters
     */
    public static Address createAddress(Long id, Users users, String title,
                                        String addressLine1, String addressLine2,
                                        String city, String country,
                                        String postalCode, String phoneNumber,
                                        LocalDate createdAt, LocalDate UpdatedAt) {
        // Check if any of the required parameters are null
        if ( Helper.isNullOrEmpty(title) ||
                Helper.isNullOrEmpty(addressLine1) ||
                Helper.isNullOrEmpty(addressLine2) ||
                Helper.isNullOrEmpty(country) ||
                Helper.isNullOrEmpty(city) ||
                Helper.isNullOrEmpty(postalCode)
        ) {
            throw new IllegalArgumentException("Required fields cannot be null in address");
        }

        // Use the Builder pattern to create a new Address object
        return new Address.Builder()
                .setId(id) // Set the ID of the address
                .setTitle(title)
                .setUsers(users)
                .setAddressLine1(addressLine1)
                .setAddressLine2(addressLine2)
                .setCity(city)
                .setCountry(country)
                .setPostalCode(postalCode)
                .setPhoneNumber(phoneNumber)
                .build();
    }
}