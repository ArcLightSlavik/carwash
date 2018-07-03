package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import ua.slavik.carwash.model.Customer;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long>
{

    Optional<Customer> findByFirstName(String john); //temporary
}
