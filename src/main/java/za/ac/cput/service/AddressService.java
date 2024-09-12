package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.repository.AddressRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AddressService implements IAddressService{
    private final AddressRepository repository;
    private static final Logger logger = Logger.getLogger(AddressService.class.getName());


    @Autowired
    AddressService(AddressRepository repository) {this.repository = repository;}

    @Override
    public Address create(Address address) {
        return repository.save(address);
    }

    @Override
    public Address read(Long id) {
        return repository.findById(id).orElse(null);}



    @Override
    public Address update(Address address) {
        if (address.getAddressID() == null) {
            throw new IllegalArgumentException("Address with the given ID does not exist.");
        }

        // Fetch existing address from the repository
        Address existingAddress = repository.findById(address.getAddressID())
                .orElseThrow(() -> new IllegalArgumentException("Address with the given ID does not exist."));

        // Update fields
        existingAddress = new Address.Builder()
                .setAddressID(existingAddress.getAddressID()) // retain original addressID
                .setUserID(address.getUserID())
                .setAddressLine(address.getAddressLine())
                .setCity(address.getCity())
                .setCountry(address.getCountry())
                .setZipCode(address.getZipCode())
                .setPhoneNumber(address.getPhoneNumber())
                .setCreatedAt(existingAddress.getCreatedAt()) // retain createdAt, typically not changed
                .setDeletedAt(address.getDeletedAt()) // set new or existing deletedAt
                .build();

        // Save the updated address
        return repository.save(existingAddress);
    }

    @Override
    public List<Address> findAll() {
       return repository.findAll();
    }


    @Override
    public Optional<Address> findByUserID(Long Id) {
        return repository.findByUserID(Id);
    }

    @Override
    public List<Address> findByAddressLine(String addressLine) {
        return repository.findByAddressLine(addressLine);
    }

    @Override
    public List<Address> findByZipcodes(String zipcode) {
        return repository.findByZipCode(zipcode);
    }

    @Override
    public List<Address> findByPhoneNumber(Integer phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);

    }

    @Override
    public List<Address> findByCountry(String country){
        return repository.findByCountry(country);
    }

    @Override
    public List<Address> findByDeletedAtIsNotNull() {
        return repository.findByDeletedAtIsNotNull();
    }

    @Override
    public List<Address> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteByAddressID(Long addressID) {

        if(!repository.existsById(addressID)){
        repository.deleteByAddressID(addressID);
        }else{
            logger.warning("Attempt to delete non-existent order with ID: " + addressID);
        }
    }


}
