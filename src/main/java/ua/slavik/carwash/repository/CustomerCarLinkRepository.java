package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.slavik.carwash.model.CustomerCarLink;

@Repository
public interface CustomerCarLinkRepository extends CrudRepository<CustomerCarLink,Long> {
}
