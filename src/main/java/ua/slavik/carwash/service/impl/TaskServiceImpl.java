package ua.slavik.carwash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Task;
import ua.slavik.carwash.repository.TaskRepository;
import ua.slavik.carwash.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository jobItemRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository jobItemRepository) {
        this.jobItemRepository = jobItemRepository;
    }

    @Override
    public Task getTaskById(Long id) {
        return jobItemRepository.findById(id).orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return jobItemRepository.save(task);
    }

    @Override
    public Task updateTask(Task task, Long id) {
        Task oldTask = getTaskById(id);
        if (oldTask == null) {
            return null;
        }

        task.setId(id);

        return jobItemRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        if (task != null) {
            jobItemRepository.deleteById(id);
        }
    }
}