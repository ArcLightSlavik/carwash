package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.slavik.carwash.model.CarJobLink;

@Repository
public interface CarJobLinkRepository extends CrudRepository<CarJobLink,Long> {
}
