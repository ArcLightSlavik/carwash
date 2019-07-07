package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.slavik.carwash.model.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
}