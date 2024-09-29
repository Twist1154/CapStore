package za.ac.cput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

/**
 * App.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 28-Sep-24
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections.singletonMap("server.servlet.context-path", "/store"));
        app.run(args);
    }
}
