package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.DTO.*;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.service.CarService;


@RestController
@RequestMapping("/car")
public class CarController
{

    private ModelMapper modelMapper =  new ModelMapper();

    @Autowired
    private CarService carService;

    @RequestMapping(method = RequestMethod.POST)
    public CarDTO createCar(@RequestBody CreateCarDTO carVO)
    {
        Car car = modelMapper.map(carVO, Car.class);
        Car savedCar = carService.createCar(car);

        return modelMapper.map(savedCar, CarDTO.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CarDTO getCar(@PathVariable("id") Long id)
    {
        return modelMapper.map(carService.getCarById(id), CarDTO.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CarDTO updateCar(@RequestBody UpdateCarDTO updateCarVO)
    {
        Car car = modelMapper.map(updateCarVO , Car.class);
        Car updatedCar = carService.updateCar(car);

        return modelMapper.map(updatedCar , CarDTO.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id)
    {
        carService.deleteCar(id);
    }
}
