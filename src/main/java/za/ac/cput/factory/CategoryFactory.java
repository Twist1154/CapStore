package za.ac.cput.factory;

import za.ac.cput.domain.Category;
import za.ac.cput.util.Helper;

/**
 * CategoryFactory.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 26-Jul-24
 */

public class CategoryFactory {

    public static Category buildCategory(Long id,
                                         String name
    ) {
        if (Helper.isNullOrEmpty(name)

        ) return null;

        return new Category.Builder()
                .setId(id)
                .setName(name)
                .build();
    }
}
