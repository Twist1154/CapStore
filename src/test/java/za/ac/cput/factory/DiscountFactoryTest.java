package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Discount;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiscountFactoryTest {

    @Order(1)
    @Test
    void buildDiscount() {
        Discount discount = DiscountFactory.buildDiscount(
                1l,
                "A1",
                "Discount for Golfer t-shirt.",
                15,
                LocalDate.now(),
                LocalDate.now(),
                5);

        assertNotNull(discount);
        System.out.println("Discount: "+ discount);
    }
}