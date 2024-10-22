/** *
 * E-commerce Web Application for selling T-shirts
 * CategoryFactory.java
 * This class uses the Factory Pattern to create an instance of the Category entity
 * Author: Mthandeni Mbobo (218223579)
 *
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Category;
import za.ac.cput.domain.SubCategory;
import za.ac.cput.util.Helper;

import java.util.Arrays;
import java.util.List;

public class CategoryFactory {

    public static Category buildCategory(Long id,
                                         String Name){
        if(Helper.isNullOrEmpty(Name))
            return null;

        return  new Category.Builder()
                .setId(id)
                .setName(Name)
                .build();
    }

    public static List<Category> getDefaultCategories(){
        return Arrays.asList(
                new Category.Builder()
                        .setId(1L)
                        .build(),

                new Category.Builder()
                        .setId(2L)
                        .build(),

                new Category.Builder()
                        .setId(3L)
                        .build()
        );
    }

}
