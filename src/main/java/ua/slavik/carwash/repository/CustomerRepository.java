package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import ua.slavik.carwash.model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long>
{

}
