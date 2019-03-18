package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.task.CreateTaskDTO;
import ua.slavik.carwash.dto.task.TaskDTO;
import ua.slavik.carwash.dto.task.UpdateTaskDTO;
import ua.slavik.carwash.model.Task;
import ua.slavik.carwash.service.TaskService;
import javax.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createTask(@Valid @RequestBody CreateTaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task savedTask = taskService.createTask(task);

        return new ResponseEntity<>(modelMapper.map(savedTask, TaskDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{taskId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getTask(@PathVariable("taskId") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(task, TaskDTO.class), HttpStatus.OK);
    }

    @PutMapping(value = "/{taskId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateTask(@RequestBody UpdateTaskDTO updateTaskDTO, @PathVariable("taskId") Long id) {
        Task oldTask = modelMapper.map(updateTaskDTO, Task.class);
        if (oldTask == null) {
            return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
        }
        Task updatedTask = taskService.updateTask(oldTask, id);

        return new ResponseEntity<>(modelMapper.map(updatedTask, TaskDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
        }
        taskService.deleteTask(id);

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
