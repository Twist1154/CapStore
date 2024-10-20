package za.ac.cput.factory;

import za.ac.cput.domain.Images;
import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

/**
 * ProductFactory.java
 *
 * Factory class for creating Product objects with embedded Images.
 */
public class ProductFactory {

    public static Product buildProduct(Long productId, String name, String description,
                                       double price, int stock, Long categoryId,
                                       LocalDateTime createdAt, LocalDateTime updatedAt,
                                       String imageUrl1, String imageUrl2, String imageUrl3, String imageUrl4) {

        // Validate required fields using the Helper class
        if (Helper.isNullOrEmpty(name) ||
                Helper.isNullOrEmpty(description) ||
                Helper.isOrderNullorEmpty(price)) {
            throw new IllegalArgumentException("Invalid input: Name, description, and price must not be null or empty.");
        }

        // Create the Images object, embedding the image URLs
        Images images = new Images(imageUrl1, imageUrl2, imageUrl3, imageUrl4);

        // Construct and return a new Product object using the builder pattern
        return new Product.Builder()
                .setId(productId)
                .setName(name)
                .setDescription(description)
                .setPrice(price)
                .setStock(stock)
                .setCategoryId(categoryId)
                .setCreatedAt(createdAt != null ? createdAt : LocalDateTime.now())  // Set current time if null
                .setUpdatedAt(updatedAt != null ? updatedAt : LocalDateTime.now())  // Set current time if null
                .setImages(images)  // Set the embedded Images object
                .build();
    }
}
