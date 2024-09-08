package za.ac.cput.factory;


import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

/**
 * Product.java
 *
 * @author Zachariah Matsimella
 * Student Num: 220097429
 * @date 06-Sep-24
 */

public class ProductFactory {
    public static Product buildProduct(Long productId, String name, String description,
                                       double price, int stock, Long categoryId,
                                       LocalDateTime createdAt, LocalDateTime updatedAt, String imagePath){
        if(Helper.isNullOrEmpty(name)||
            Helper.isNullOrEmpty(description)||
            Helper.isOrderNullorEmpty(price)||
            Helper.isNullOrEmpty(imagePath)
        ){
            return null;
        }
        return new Product.Builder()
                .setProductId(productId)
                .setName(name)
                .setDescription(description)
                .setPrice(price)
                .setStock(stock)
                .setCategoryId(categoryId)
                .setCreatedAt(createdAt)
                .setUpdatedAt(updatedAt)
                .setImagePath(imagePath)
                .build();
    }
}
