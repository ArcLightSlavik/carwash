package ua.slavik.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Task;
import ua.slavik.carwash.repository.TaskRepository;
import ua.slavik.carwash.service.TaskService;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository jobItemRepository;

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