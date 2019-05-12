package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.slavik.carwash.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}