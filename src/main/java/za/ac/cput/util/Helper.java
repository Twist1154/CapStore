package za.ac.cput.util;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Helper.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 07-Sep-24
 */

public class Helper {

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNullOrEmpty(Integer i) {
        return i == null;
    }

    public static boolean isDoubleNullOrEmpty(Double s) {
        return s == null || s.isNaN();
    }

    public static boolean isOrderNullorEmpty(Double s) {
        return s == null || Double.isNaN(s);
    }

    public static boolean isNullOrEmpty(Object s) {
        return s == null || s.toString().isEmpty();
    }

    public static boolean isValidRange(int rating) {
        return rating >= 1 && rating <= 5;
    }

    public static boolean isEmailValid(String email, String regex) {
        return Pattern.compile(regex)
                .matcher(email)
                .matches();
    }

    public static boolean isNullOrEmpty(Long l) {
        return l == null;
    }
}
