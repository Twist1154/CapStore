package za.ac.cput.service;

import za.ac.cput.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface IProductService extends IService<Product, Long>{
    void delete(Long id);

    List<Product> findByName(String name);

    List<Product> findByDescription(String description);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByCreatedAt(LocalDateTime createdAt);

    List<Product> findByUpdatedAt(LocalDateTime updatedAt);

    List<Product> searchProducts(String keyWord);
}
