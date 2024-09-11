package za.ac.cput.service;

import za.ac.cput.domain.Address;

import java.util.List;
import java.util.Optional;

/**
 * IAddressService defines the contract for address-related operations.
 * It extends the generic IService interface, providing specific methods
 * for managing {@link Address} entities. This includes finding addresses by
 * user ID, address line, zip code, country, phone number, and checking for
 * soft-deleted addresses.
 */
public interface IAddressService extends IService<Address, Long> {

    /**
     * Finds an optional address by the associated user's ID.
     *
     * @return an Optional containing the address if found, or an empty Optional if not found
     */
    //Optional<Address> findByUserId(Long id);

    Optional<Address> findByUserID(Long Id);

    /**
     * Finds a list of addresses by the address line.
     *
     * @param addressLine the address line to search for
     * @return a list of addresses that match the given address line
     */
    List<Address> findByAddressLine(String addressLine);

    /**
     * Finds a list of addresses by the zip code.
     *
     * @param zipcode the zip code to search for
     * @return a list of addresses that match the given zip code
     */
    List<Address> findByZipcodes(String zipcode);

    /**
     * Finds a list of addresses by the phone number.
     *
     * @param phoneNumber the phone number to search for
     * @return a list of addresses that match the given phone number
     */
    List<Address> findByPhoneNumber(Integer phoneNumber);

    /**
     * Finds a list of addresses by the country.
     *
     * @param country the country to search for
     * @return a list of addresses that match the given country
     */
    List<Address> findByCountry(String country);

    /**
     * Finds a list of addresses that have been soft-deleted (i.e., have a non-null deletedAt field).
     *
     * @return a list of addresses with a non-null deletedAt timestamp
     */
    List<Address> findByDeletedAtIsNotNull();

    /**
     * Retrieves all addresses.
     *
     * @return a list of all addresses
     */
    List<Address> getAll();

    void deleteByAddressID(Long id);
}
