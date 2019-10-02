package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.model.dto.car.CarDTO;
import ua.slavik.carwash.model.dto.car.CreateCarDTO;
import ua.slavik.carwash.model.dto.car.UpdateCarDTO;
import ua.slavik.carwash.service.CarService;
import ua.slavik.carwash.service.CustomerService;
import ua.slavik.carwash.service.JobService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarController {
    private final ModelMapper modelMapper;
    private final CarService carService;
    private final CustomerService customerService;
    private final JobService jobService;

    @PostMapping
    public ResponseEntity createCar(@Valid @RequestBody CreateCarDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);
        car.setCustomer(customerService.getCustomerById(carDTO.getCustomerId()));
        car.setJob(jobService.getJobById(carDTO.getJobId()));
        Car savedCar = carService.createCar(car);

        return ResponseEntity.status(CREATED)
                .body(modelMapper.map(savedCar, CarDTO.class));
    }

    @GetMapping(value = "/{carId}")
    public ResponseEntity getCar(@PathVariable("carId") Long id) {
        Car car = carService.getCarById(id);

        return ResponseEntity.status(OK)
                .body(modelMapper.map(car, CarDTO.class));
    }

    @PutMapping(value = "/{carId}")
    public ResponseEntity updateCar(@RequestBody UpdateCarDTO updateCarDTO, @PathVariable("carId") Long id) {
        Car oldCar = modelMapper.map(updateCarDTO, Car.class);
        oldCar.setCustomer(customerService.getCustomerById(updateCarDTO.getCustomerId()));
        oldCar.setJob(jobService.getJobById(updateCarDTO.getJobId()));
        Car updatedCar = carService.updateCar(oldCar, id);
        updatedCar.setCustomer(customerService.getCustomerById(updateCarDTO.getCustomerId()));
        updatedCar.setJob(jobService.getJobById(updateCarDTO.getJobId()));

        return ResponseEntity.status(OK)
                .body(modelMapper.map(updatedCar, CarDTO.class));
    }

    @DeleteMapping(value = "/{carId}")
    public ResponseEntity deleteCar(@PathVariable("carId") Long id) {
        carService.deleteCar(id);

        return ResponseEntity.status(NO_CONTENT)
                .build();
    }
}
