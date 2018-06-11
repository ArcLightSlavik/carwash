package ua.slavik.carwash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.repository.CarRepository;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService
{
    @Autowired
    private CarRepository carRepository;

    @Override
    public Optional<Car> getCarById(Long id)
    {
        return carRepository.findById(id);
    }

    @Override
    public Car createCar(Car car)
    {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car)
    {
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id)
    {
        Optional<Car> car = getCarById(id);
        if (car != null)
        {
            carRepository.deleteById(id);
        }
    }
}
