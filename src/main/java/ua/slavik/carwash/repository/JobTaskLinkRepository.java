package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.slavik.carwash.model.JobTaskLink;

@Repository
public interface JobTaskLinkRepository extends CrudRepository<JobTaskLink,Long> {
}
