package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import ua.slavik.carwash.model.CustomerCarLink;

public interface CustomerCarLinkRepository extends CrudRepository<CustomerCarLink,Long> {
}
