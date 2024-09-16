package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Address;

import java.time.LocalDate;
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
     * Finds all Addresses associated with a given User.
     *
     * @param id the User entity to search by
     * @return a list of Addresses associated with the given User
     */
    Optional<Address> findByUserId(Long id);

    /**
     * Finds all Addresses with a given title.
     *
     * @param title the title to search by
     * @return a list of Addresses with the given title
     */
    List<Address> findByTitle(String title);

    /**
     * Finds all Addresses with a given address line 1.
     *
     * @param addressLine1 the address line 1 to search by
     * @return a list of Addresses with the given address line 1
     */
    List<Address> findByAddressLine1(String addressLine1);

    /**
     * Finds all Addresses with a given address line 2.
     *
     * @param addressLine2 the address line 2 to search by
     * @return a list of Addresses with the given address line 2
     */
    List<Address> findByAddressLine2(String addressLine2);

    /**
     * Finds all Addresses in a given country.
     *
     * @param country the country to search by
     * @return a list of Addresses in the given country
     */
    List<Address> findByCountry(String country);

    /**
     * Finds all Addresses in a given city.
     *
     * @param city the city to search by
     * @return a list of Addresses in the given city
     */
    List<Address> findByCity(String city);

    /**
     * Finds all Addresses with a given postal code.
     *
     * @param postalCode the postal code to search by
     * @return a list of Addresses with the given postal code
     */
    List<Address> findByPostalCode (String postalCode);

    /**
     * Finds all Addresses with a given phone number.
     *
     * @param phoneNumber the phone number to search by
     * @return a list of Addresses with the given phone number
     */
    List<Address> findByPhoneNumber(String phoneNumber);

    /**
     * Finds all Addresses created after a given date.
     *
     * @param createdAt the date to search by
     * @return a list of Addresses created after the given date
     */
    List<Address> findByCreatedAtAfter(LocalDate createdAt);

    /**
     * Finds all Addresses that have a non-null deletedAt field.
     *
     * @return a list of Addresses that have been marked as deleted
     */
    List<Address> findByUpdatedAt(LocalDate updatedAt);
}
