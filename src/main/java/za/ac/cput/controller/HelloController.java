package za.ac.cput.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

/**
 * HelloController.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 04-Oct-24
 */

@RestController
public class HelloController {

    @GetMapping("/")
    public String greet(HttpServletRequest request) {
        return "Welcome to Twist "+request.getSession().getId();
    }

}