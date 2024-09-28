package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.IProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product read(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product update(Product product) {
        // Check if product exists, update if it does, otherwise return null
        return productRepository.findById(product.getProductId()).map(existingProduct -> {
            Product updatedProduct = new Product.Builder()
                    .copy(existingProduct)
                    .setProductId(product.getProductId())
                    .setName(product.getName())
                    .setDescription(product.getDescription())
                    .setPrice(product.getPrice())
                    .setStock(product.getStock())
                    .setCategoryId(product.getCategoryId())
                    .setCreatedAt(product.getCreatedAt())
                    .setUpdatedAt(product.getUpdatedAt())
                    .setImages(product.getImages())
                    .build();
            return productRepository.save(updatedProduct);
        }).orElseGet(() -> {
            System.out.println("Attempt to update non-existing product with ID: " + product.getProductId());
            return null;
        });
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findByDescription(String description) {
        return productRepository.findByDescription(description);
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> findByPriceBetween(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> findByCreatedAt(LocalDateTime createdAt) {
        return productRepository.findByCreatedAt(createdAt);
    }

    @Override
    public List<Product> findByUpdatedAt(LocalDateTime updatedAt) {
        return productRepository.findByUpdatedAt(updatedAt);
    }
}
