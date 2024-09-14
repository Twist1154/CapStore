package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Discount;
import za.ac.cput.factory.DiscountFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class DiscountServiceTest {
    @Autowired
    private DiscountService discountService;

    private static Discount discount1, discount2, discount3;

    @BeforeEach
    void setUp() {
        discount1 = DiscountFactory.buildDiscount(
                1l,
                "A1",
                "Discount for Golfer t-shirt.",
                10,
                LocalDate.now(),
                LocalDate.now(),
                5
        );

        discount2 = DiscountFactory.buildDiscount(
                1l,
                "B1",
                "Discount for V-neck t-shirt.",
                15,
                LocalDate.now(),
                LocalDate.now(),
                10
        );

        discount3 = DiscountFactory.buildDiscount(
                1l,
                "C1",
                "Discount for Crew neck t-shirt.",
                20,
                LocalDate.now(),
                LocalDate.now(),
                15
        );
    }

    @Test
    void create() {
        Discount createDiscount1 = discountService.create(discount1);
        assertNotNull(createDiscount1);
        System.out.println("Discount 1: "+ createDiscount1);

        Discount createDiscount2 = discountService.create(discount2);
        assertNotNull(createDiscount2);
        System.out.println("Discount 2: "+ createDiscount2);

        Discount createDiscount3 = discountService.create(discount3);
        assertNotNull(createDiscount3);
        System.out.println("Discount 3: "+ createDiscount3);
    }

    @Test
    void read() {
//        Discount readDiscount1 = discountService.read(discount1.getDiscountId());
        Discount readDiscount1 = discountService.read(1L);
        assertNotNull(readDiscount1);
        assertEquals(discount1.getDiscountId(), readDiscount1.getDiscountId());
        System.out.println("Read discount 1: "+ readDiscount1);

        Discount readDiscount2 = discountService.read(discount2.getDiscountId());
        assertNotNull(readDiscount2);
        assertEquals(discount2.getDiscountId(), readDiscount2.getDiscountId());
        System.out.println("Read discount 1: "+ readDiscount2);
    }

    @Test
    void update() {
        System.out.println("Discount 2 before update: "+ discount2);

        Discount updateDiscount2 = new Discount.Builder()
                .setDiscount_id(discount2.getDiscountId())
                .setCode("B2")
                .setDescription("Discount for V-neck t-shirt.")
                .setDiscount_percent(15)
                .setStart_date(LocalDate.now())
                .setEnd_date(LocalDate.now())
                .setMax_uses(8)
                .build();

        Discount result = discountService.update(updateDiscount2);
        assertNotNull(result);
        assertEquals(updateDiscount2.getDiscountId(), result.getDiscountId());
        System.out.println(result);
    }

    @Test
    void delete() {
        System.out.println("Discount to be deleted: "+ discount3);
        Long discount3Id = discount3.getDiscountId();
        discountService.delete(discount3Id);

        Discount deleteDiscount = discountService.read(discount3Id);
        assertNull(deleteDiscount);
        System.out.println("Deleted status: "+ deleteDiscount);
    }

    @Test
    void findAll() {
        List<Discount> discountList = discountService.findAll();
        assertFalse(discountList.isEmpty());
        System.out.println(discountList);
    }
}