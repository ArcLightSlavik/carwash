package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Task;

public interface TaskService {
    Task getTaskById(Long id);
    Task createTask(Task task);
    Task updateTask(Task task, Long id);
    void deleteTask(Long id);
}
