package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.slavik.carwash.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
}