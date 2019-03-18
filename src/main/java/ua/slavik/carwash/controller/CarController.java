package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.car.CarDTO;
import ua.slavik.carwash.dto.car.CreateCarDTO;
import ua.slavik.carwash.dto.car.UpdateCarDTO;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.service.CarService;
import javax.validation.Valid;

@RestController
@RequestMapping("/car")
public class CarController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createCar(@Valid @RequestBody CreateCarDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);
        Car savedCar = carService.createCar(car);

        return new ResponseEntity<>(modelMapper.map(savedCar, CarDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{carId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getCar(@PathVariable("carId") Long id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(car, CarDTO.class), HttpStatus.OK);
    }

    @PutMapping(value = "/{carId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateCar(@RequestBody UpdateCarDTO updateCarDTO, @PathVariable("carId") Long id) {
        Car oldCar = modelMapper.map(updateCarDTO, Car.class);
        if (oldCar == null) {
            return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
        }
        Car updatedCar = carService.updateCar(oldCar, id);

        return new ResponseEntity<>(modelMapper.map(updatedCar, CarDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{carId}")
    public ResponseEntity deleteCar(@PathVariable("carId") Long id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
        }
        carService.deleteCar(id);

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
