package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import ua.slavik.carwash.model.JobItem;

public interface JobItemRepository extends CrudRepository<JobItem, Long>
{

}
