package ua.slavik.carwash.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ua.slavik.carwash.model.*;
import ua.slavik.carwash.model.enums.Status;
import ua.slavik.carwash.repository.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DatabaseStarter implements ApplicationRunner {
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;
    private final TaskRepository taskRepository;

    @Override
    public void run(ApplicationArguments args) {
        jobRepository.save(new Job(1L, Status.IN_PROGRESS, LocalDateTime.now(), LocalDateTime.now().plusDays(5), carRepository.findById(1L).orElse(null), Collections.singletonList(taskRepository.findById(1L).orElse(null))));
        jobRepository.save(new Job(2L, Status.NOT_STARTED, LocalDateTime.now(), LocalDateTime.now().plusDays(10), carRepository.findById(1L).orElse(null), Collections.singletonList(taskRepository.findById(1L).orElse(null))));
        jobRepository.save(new Job(3L, Status.TERMINATED, LocalDateTime.now(), LocalDateTime.now().plusDays(1), carRepository.findById(1L).orElse(null), Collections.singletonList(taskRepository.findById(1L).orElse(null))));
        jobRepository.save(new Job(4L, Status.COMPLETED, LocalDateTime.now(), LocalDateTime.now().plusDays(5), carRepository.findById(1L).orElse(null), Collections.singletonList(taskRepository.findById(1L).orElse(null))));

        taskRepository.save(new Task(1L, 10, 5, 10, "Wash", "Washing the car", Status.NOT_STARTED, true, jobRepository.findById(1L).orElse(null)));
        taskRepository.save(new Task(2L, 50, 30, 5, "Interior cleaning", "Cleaning inside of a car", Status.IN_PROGRESS, false, jobRepository.findById(2L).orElse(null)));
        taskRepository.save(new Task(3L, 100, 10, 3, "Polishing", "Polishing outside of the car", Status.COMPLETED, false, jobRepository.findById(3L).orElse(null)));
        taskRepository.save(new Task(4L, 250, 50, 10, "Scratches", "Remove scratches from outside the car", Status.TERMINATED, true, jobRepository.findById(4L).orElse(null)));

        customerRepository.save(new Customer(1L, "Michael", "Corleone", "123456565", "michael.corleone@gmail.com", Arrays.asList(carRepository.findById(1L).orElse(null), carRepository.findById(2L).orElse(null))));
        customerRepository.save(new Customer(2L, "Kevin", "McCallister", "457664324", "kevin.mccallister@gmail.com", Collections.singletonList(carRepository.findById(3L).orElse(null))));
        customerRepository.save(new Customer(3L, "Ron", "Burgundy", "345347658", "ron.burgundy@gmail.com", Collections.singletonList(carRepository.findById(4L).orElse(null))));
        customerRepository.save(new Customer(4L, "The", "Dude", "658745443", "the.dude@gmail.com", Collections.singletonList(carRepository.findById(1L).orElse(null))));

        employeeRepository.save(new Employee(1L, 30, "Patrick", "Bateman", "45437636", "patrick.bateman@gmail.com"));
        employeeRepository.save(new Employee(2L, 55, "Walter", "Sobchak", "65464396", "walter.sobchak@gmail.com"));
        employeeRepository.save(new Employee(3L, 43, "Lester", "Burnham", "23482384", "lester.burnham@gmail.com"));
        employeeRepository.save(new Employee(4L, 29, "Hans", "Landa", "769745689", "hans.landa@gmail.com"));

        carRepository.save(new Car(1L, "AA 0114 AP", "BMW", customerRepository.findById(3L).orElse(null), jobRepository.findById(1L).orElse(null)));
        carRepository.save(new Car(2L, "AA 9872 XX", "Lamborghini", customerRepository.findById(4L).orElse(null), jobRepository.findById(3L).orElse(null)));
        carRepository.save(new Car(3L, "062-02 AP", "Ferrari", customerRepository.findById(1L).orElse(null), jobRepository.findById(4L).orElse(null)));
        carRepository.save(new Car(4L, "11 BB 0714", "Mercedes-Benz", customerRepository.findById(3L).orElse(null), jobRepository.findById(2L).orElse(null)));
    }
}
