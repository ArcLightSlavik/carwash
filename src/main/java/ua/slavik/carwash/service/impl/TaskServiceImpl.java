package ua.slavik.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Task;
import ua.slavik.carwash.model.enums.Status;
import ua.slavik.carwash.repository.TaskRepository;
import ua.slavik.carwash.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task, Long id) {
        Task oldTask = getTaskById(id);
        if (oldTask == null) {
            return null;
        }

        task.setId(id);

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        if (task != null) {
            taskRepository.deleteById(id);
        }
    }

    @Override
    public List<Task> getTaskListByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }
}