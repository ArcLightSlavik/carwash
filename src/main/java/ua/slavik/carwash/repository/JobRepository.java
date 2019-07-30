package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.enums.JobStatus;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    List<Job> findByStatus(JobStatus status);
}