package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.slavik.carwash.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
}