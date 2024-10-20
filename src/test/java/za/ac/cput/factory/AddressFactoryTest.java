package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AddressFactoryTest {
    private User user;
    @Test
    void buildAddress() {
        Address address = AddressFactory.createAddress(
                1L,
                user,
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

        assertNotNull(address);
        System.out.println(address);
    }
}