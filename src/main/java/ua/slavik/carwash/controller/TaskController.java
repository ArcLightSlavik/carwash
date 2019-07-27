package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.model.Task;
import ua.slavik.carwash.model.dto.task.CreateTaskDTO;
import ua.slavik.carwash.model.dto.task.TaskDTO;
import ua.slavik.carwash.model.dto.task.UpdateTaskDTO;
import ua.slavik.carwash.service.TaskService;

import javax.validation.Valid;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {
    private final ModelMapper modelMapper;
    private final TaskService taskService;
    private static final String TASK_NOT_FOUND = "Task by id you entered wasn't found.";

    @PostMapping
    public ResponseEntity createTask(@Valid @RequestBody CreateTaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task savedTask = taskService.createTask(task);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedTask, TaskDTO.class));
    }

    @GetMapping(value = "/{taskId}")
    public ResponseEntity getTask(@PathVariable("taskId") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(TASK_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(task, TaskDTO.class));
    }

    @PutMapping(value = "/{taskId}")
    public ResponseEntity updateTask(@RequestBody UpdateTaskDTO updateTaskDTO, @PathVariable("taskId") Long id) {
        Task oldTask = modelMapper.map(updateTaskDTO, Task.class);
        if (oldTask == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(TASK_NOT_FOUND);
        }
        Task updatedTask = taskService.updateTask(oldTask, id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(updatedTask, TaskDTO.class));
    }

    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(TASK_NOT_FOUND);
        }
        taskService.deleteTask(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Task has been deleted.");
    }
}
