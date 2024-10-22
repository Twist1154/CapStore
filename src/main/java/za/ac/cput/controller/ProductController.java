package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Product;
import za.ac.cput.service.IProductService;

import java.time.LocalDateTime;
import java.util.List;

/*
 *Product:java
 *Product: Controller Class
 * Author: Zachariah Matsimella
 * Date: 19 May 2024
 */

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.create(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.read(id);
        return product != null ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!id.equals(product.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product updatedProduct = productService.update(product);
        return updatedProduct != null ? new ResponseEntity<>(updatedProduct, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productService.read(id);
        if (product != null) {
        productService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String name) {
        List<Product> products = productService.findByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<Product>> getProductByDescription(@PathVariable String description) {
        List<Product> products = productService.findByDescription(description);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductByCategoryId(@PathVariable long categoryId) {
        List<Product> products = productService.findByCategories_Id(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice
    ) {
        List<Product> products = productService.findByPriceBetween(minPrice, maxPrice);
        System.out.println(products);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/created-after")
    public ResponseEntity<List<Product>> getProductCreatedAfter(@RequestParam LocalDateTime createdAt) {
        List<Product> products = productService.findByCreatedAt(createdAt);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/updated-before")
    public ResponseEntity<List<Product>> getProductUpdatedBefore(@RequestParam LocalDateTime updatedAt) {
        List<Product> products = productService.findByUpdatedAt(updatedAt);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyWord){
        System.out.println("Searching for: " + keyWord);
        List<Product> products = productService.searchProducts(keyWord);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
