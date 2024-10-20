/** *
 * E-commerce Web Application for selling T-shirts
 * CategoryFactory.java
 * This class uses the Factory Pattern to create an instance of the Category entity
 * Author: Mthandeni Mbobo (218223579)
 *
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Category;
import za.ac.cput.util.Helper;

import java.util.Arrays;
import java.util.List;

public class CategoryFactory {

    public static Category buildCategory(Long categoryId, String categoryName, String subCategoryName){
        if(Helper.isNullOrEmpty(categoryName) || Helper.isNullOrEmpty(subCategoryName))
            return null;

        return  new Category.Builder()
                .setCategoryId(categoryId)
                .setCategoryName(categoryName)
                .setSubCategoryName(subCategoryName)
                .build();
    }

    public static List<Category> getDefaultCategories(){
        return Arrays.asList(
                new Category.Builder()
                        .setCategoryId(1L)
                        .setCategoryName("Men")
                        .setSubCategoryName("T-Shirts")
                        .build(),

                new Category.Builder()
                        .setCategoryId(2L)
                        .setCategoryName("Women")
                        .setSubCategoryName("T-Shirts")
                        .build(),

                new Category.Builder()
                        .setCategoryId(3L)
                        .setCategoryName("Kids")
                        .setSubCategoryName("T-Shirts")
                        .build()
        );
    }

}
