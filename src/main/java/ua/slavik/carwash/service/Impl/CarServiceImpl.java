package ua.slavik.carwash.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.repository.CarRepository;
import ua.slavik.carwash.service.CarService;

@Service
public class CarServiceImpl implements CarService
{
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }

    @Override
    public Car getCarById(Long id)
    {
        return carRepository.findById(id).orElse(null);
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
        Car car = getCarById(id);
        if (car != null)
        {
            carRepository.deleteById(id);
        }
    }
}
