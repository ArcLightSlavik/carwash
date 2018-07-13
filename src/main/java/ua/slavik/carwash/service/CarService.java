package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Car;

public interface CarService {
    Car getCarById(Long id);

    Car createCar(Car car);

    Car updateCar(Car car);

    void deleteCar(Long id);
}
