package ua.slavik.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.exception.custom.NotFoundException;
import ua.slavik.carwash.model.Task;
import ua.slavik.carwash.model.enums.Status;
import ua.slavik.carwash.repository.TaskRepository;
import ua.slavik.carwash.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task, Long id) {
        getTaskById(id);
        task.setId(id);

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTaskListByStatus(Status status) {
        List<Task> taskList = taskRepository.findByStatus(status);
        if (taskList.size() == 0) {
            throw new NotFoundException();
        }
        return taskList;
    }
}