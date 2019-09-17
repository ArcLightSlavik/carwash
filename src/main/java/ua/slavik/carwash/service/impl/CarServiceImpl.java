package ua.slavik.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.exception.custom.NotFoundException;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.repository.CarRepository;
import ua.slavik.carwash.service.CarService;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car, Long id) {
        getCarById(id);
        car.setId(id);

        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        getCarById(id);
        carRepository.deleteById(id);
    }
}
