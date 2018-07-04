package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.DTO.CarDTO.CarDTO;
import ua.slavik.carwash.DTO.CarDTO.CreateCarDTO;
import ua.slavik.carwash.DTO.CarDTO.UpdateCarDTO;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.service.CarService;


@RestController
@RequestMapping("/car")
public class CarController
{
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity createCar(@RequestBody CreateCarDTO carDTO)
    {
        Car car = modelMapper.map(carDTO, Car.class);
        Car savedCar = carService.createCar(car);

        return new ResponseEntity(modelMapper.map(savedCar, CarDTO.class), HttpStatus.OK);
    }

    @GetMapping(value = "/{carId}")
    public ResponseEntity getCar(@PathVariable("carId") Long id)
    {
        Car car = carService.getCarById(id);
        if (car == null)
        {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(modelMapper.map(car, CarDTO.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateCar(@RequestBody UpdateCarDTO updateCarDTO)
    {
        Car car = modelMapper.map(updateCarDTO, Car.class);
        Car updatedCar = carService.updateCar(car);

        return new ResponseEntity(modelMapper.map(updatedCar, CarDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{carId}")
    public ResponseEntity delete(@PathVariable("carId") Long id)
    {
        Car car = carService.getCarById(id);
        if (car == null)
        {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        carService.deleteCar(id);
        return new ResponseEntity("deleted", HttpStatus.OK);
    }
}
