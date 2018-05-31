package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import ua.slavik.carwash.model.Service;

public interface ServiceRepository extends CrudRepository<Service, Long>
{
}
