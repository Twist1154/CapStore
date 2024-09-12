package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Address;

import java.util.List;
import java.util.Optional;

/**
 * AddressRepository is a repository interface for managing {@link Address} entities.
 * It provides methods for performing CRUD operations and custom queries related to addresses.
 * This repository extends JpaRepository, which provides JPA functionalities for the Address entity.
 *
 * <p>Annotated with {@code @Repository} to indicate that it's a Spring Data repository.</p>
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Finds a list of addresses by the given address line.
     *
     * @param addressLine the address line to search for
     * @return a list of addresses that match the given address line
     */
    List<Address> findByAddressLine(String addressLine);

    /**
     * Finds a list of addresses by the given zip code.
     *
     * @param zipCode the zip code to search for
     * @return a list of addresses that match the given zip code
     */
    List<Address> findByZipCode(String zipCode);

    /**
     * Finds a list of addresses by the given country.
     *
     * @param country the country to search for
     * @return a list of addresses that match the given country
     */
    List<Address> findByCountry(String country);

    /**
     * Finds a list of addresses by the given phone number.
     *
     * @param phoneNumber the phone number to search for
     * @return a list of addresses that match the given phone number
     */
    List<Address> findByPhoneNumber(Integer phoneNumber);

    /**
     * Finds an optional address by the given user ID.
     *
     * @param id the user ID to search for
     * @return an Optional containing the address if found, or an empty Optional if not found
     */
    Optional<Address> findByUserID(Long id);

    /**
     * Finds a list of addresses that have been soft-deleted.
     * Addresses with a non-null {@code deletedAt} field are considered soft-deleted.
     *
     * @return a list of addresses that have been soft-deleted
     */
    List<Address> findByDeletedAtIsNotNull();

    void deleteByAddressID(Long addressID);
}
