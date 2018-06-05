package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Car;
import java.util.Optional;

public interface CarService
{
    Optional<Car> getCarById(Long id);

    Car createCar(Car car);

    Car updateCar(Car car);

    void deleteCar(Long id);
}
