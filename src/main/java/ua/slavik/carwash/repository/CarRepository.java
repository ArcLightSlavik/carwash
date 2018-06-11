package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import ua.slavik.carwash.model.Car;

public interface CarRepository extends CrudRepository<Car, Long>
{

}