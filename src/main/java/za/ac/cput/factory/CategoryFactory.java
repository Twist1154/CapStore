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

}
