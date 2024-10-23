package za.ac.cput.factory;

import za.ac.cput.domain.Discount;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class DiscountFactory {
    public static Discount buildDiscount(Long id,
                                         String code,
                                         String description,
                                         double discount_percent,
                                         LocalDate start_date,
                                         LocalDate end_date,
                                         int max_uses) {

        if (Helper.isNullOrEmpty(code) ||
                Helper.isNullOrEmpty(description) ||
                Helper.isOrderNullorEmpty(discount_percent) ||
                Helper.isNullOrEmpty(max_uses)
        ) return null;

        return new Discount.Builder()
                .setId(id)
                .setCode(code)
                .setDescription(description)
                .setDiscountPercent(discount_percent)
                .setStartDate(start_date)
                .setEndDate(end_date)
                .setMaxUses(max_uses)
                .build();
    }
}
