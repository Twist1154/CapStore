package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.repository.AddressRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AddressService implements IAddressService {

    private final AddressRepository addressRepository;
    private static final Logger logger = Logger.getLogger(AddressService.class.getName());

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * @param address The entity to be created.
     * @return
     */
    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    /**
     * @param id The ID of the entity to be read.
     * @return address
     */
    @Override
    public Address read(Long id) {
        return addressRepository.findById(id).orElse(null);
    }


    @Override
    public Address update(Address address) {
        if(address.getUserId() == null || !addressRepository.existsById(address.getUserId())){
            throw new IllegalArgumentException("Address with the given UserID does not exist.");
        }
        Address updatedAddress = new Address.Builder()
                .setUserId(address.getUserId())
                .setTitle(address.getTitle())
                .setAddressLine1(address.getAddressLine1())
                .setAddressLine2(address.getAddressLine2())
                .setCity(address.getCity())
                .setCountry(address.getCountry())
                .setZipCode(address.getZipCode())
                .setPhoneNumber(address.getPhoneNumber())
                .setCreatedAt(address.getCreatedAt())
                .setUpdatedAt(address.getUpdatedAt())
                .build();
        return addressRepository.save(updatedAddress);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }



    public void delete(Long id) {
        if(!addressRepository.existsById(id)){
            addressRepository.deleteById(id);
        }else{
            logger.warning("Attempt to delete non-existent order with ID: " + id);
        }
    }

    @Override
    public Optional<Address> findByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public List<Address> findByTitle(String title) {
        return addressRepository.findByTitle(title);
    }

    @Override
    public List<Address> findByAddressLine1(String addressLine1) {
        return addressRepository.findByAddressLine1(addressLine1);
    }

    @Override
    public List<Address> findByAddressLine2(String addressLine2) {
        return addressRepository.findByAddressLine2(addressLine2);
    }

    @Override
    public List<Address> findByCountry(String country) {
        return addressRepository.findByCountry(country);
    }

    @Override
    public List<Address> findByCity(String city) {
        return addressRepository.findByCity(city);
    }

    @Override
    public List<Address> findByPostalCode(String zipCode) {
        return addressRepository.findByZipCode(zipCode);
    }

    @Override
    public List<Address> findByPhoneNumber(Integer phoneNumber) {
        return addressRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Address> findByCreatedAtAfter(LocalDate createdAt) {
        return addressRepository.findByCreatedAtAfter(createdAt);
    }

    @Override
    public List<Address> findByUpdatedAt(LocalDate updatedAt) {
        return addressRepository.findByUpdatedAt(updatedAt);
    }
}
