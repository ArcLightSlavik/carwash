package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.car.CarDTO;
import ua.slavik.carwash.dto.car.CreateCarDTO;
import ua.slavik.carwash.dto.car.UpdateCarDTO;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.service.CarService;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final ModelMapper modelMapper;
    private final CarService carService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createCar(@Valid @RequestBody CreateCarDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);
        Car savedCar = carService.createCar(car);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedCar, CarDTO.class));
    }

    @GetMapping(value = "/{carid}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getCar(@PathVariable("carid") Long id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return new ResponseEntity<>("Car by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(car, CarDTO.class));
    }

    @PutMapping(value = "/{carid}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateCar(@RequestBody UpdateCarDTO updateCarDTO, @PathVariable("carid") Long id) {
        Car oldCar = modelMapper.map(updateCarDTO, Car.class);
        if (oldCar == null) {
            return new ResponseEntity<>("Car by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        Car updatedCar = carService.updateCar(oldCar, id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(updatedCar, CarDTO.class));
    }

    @DeleteMapping(value = "/{carid}")
    public ResponseEntity deleteCar(@PathVariable("carid") Long id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return new ResponseEntity<>("Car by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        carService.deleteCar(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Car has been deleted.");
    }
}
