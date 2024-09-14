package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Orders;
import za.ac.cput.domain.Product;
import za.ac.cput.service.IProductService;

import java.time.LocalDate;
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
    public Product createProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Product> readProduct(@PathVariable Long id) {
        Product product = productService.read(id);
        if(product != null){
            return ResponseEntity.ok(product);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productService.read(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product updatedProduct = productService.update(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }
}