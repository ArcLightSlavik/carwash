package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.slavik.carwash.model.JobItem;

@Repository
public interface JobItemRepository extends CrudRepository<JobItem,Long> {
}
