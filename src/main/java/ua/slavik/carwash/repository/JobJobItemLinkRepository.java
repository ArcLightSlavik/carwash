package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.slavik.carwash.model.JobJobItemLink;

@Repository
public interface JobJobItemLinkRepository extends CrudRepository<JobJobItemLink,Long> {
}
