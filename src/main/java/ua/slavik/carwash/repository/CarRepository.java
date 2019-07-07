package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.slavik.carwash.model.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
}