package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.domain.Product;
import za.ac.cput.service.IProductService;

import java.util.List;

/**
 * PublicController.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 22-Oct-24
 */
@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "*")
public class PublicController {

    private final IProductService productService;

    @Autowired
    private PublicController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
