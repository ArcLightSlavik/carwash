package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import ua.slavik.carwash.model.Job;

public interface JobRepository extends CrudRepository<Job, Long>
{
    void delete(Long id);
}