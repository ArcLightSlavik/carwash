package ua.slavik.carwash.repository;

import org.springframework.data.repository.CrudRepository;
import ua.slavik.carwash.model.Task;

public interface TaskRepository extends CrudRepository<Task,Long> {
}
