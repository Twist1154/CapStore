package za.ac.cput.factory;

import za.ac.cput.domain.Discount;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class DiscountFactory {
    public static Discount buildDiscount(long discount_id, String code, String description,
                                         double discount_percent, LocalDate start_date,
                                         LocalDate end_date, int max_uses) {

        if (Helper.isNullOrEmpty(code) ||
                Helper.isNullOrEmpty(description) ||
                Helper.isOrderNullorEmpty(discount_percent) ||
                Helper.isNullOrEmpty(max_uses)
        ) return null;

        return new Discount.Builder()
                .setDiscount_id(discount_id)
                .setCode(code)
                .setDescription(description)
                .setDiscount_percent(discount_percent)
                .setStart_date(start_date)
                .setEnd_date(end_date)
                .setMax_uses(max_uses)
                .build();
    }
}
