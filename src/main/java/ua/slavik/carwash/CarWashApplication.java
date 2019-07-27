package ua.slavik.carwash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarWashApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(CarWashApplication.class);
        springApplication.run(args);
    }
}
