package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.task.CreateTaskDTO;
import ua.slavik.carwash.dto.task.TaskDTO;
import ua.slavik.carwash.dto.task.UpdateTaskDTO;
import ua.slavik.carwash.model.Task;
import ua.slavik.carwash.service.TaskService;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final ModelMapper modelMapper;
    private final TaskService taskService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createTask(@Valid @RequestBody CreateTaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task savedTask = taskService.createTask(task);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedTask, TaskDTO.class));
    }

    @GetMapping(value = "/{taskid}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getTask(@PathVariable("taskid") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>("Task by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(task, TaskDTO.class));
    }

    @PutMapping(value = "/{taskid}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateTask(@RequestBody UpdateTaskDTO updateTaskDTO, @PathVariable("taskid") Long id) {
        Task oldTask = modelMapper.map(updateTaskDTO, Task.class);
        if (oldTask == null) {
            return new ResponseEntity<>("Task by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        Task updatedTask = taskService.updateTask(oldTask, id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(updatedTask, TaskDTO.class));
    }

    @DeleteMapping(value = "/{taskid}")
    public ResponseEntity deleteTask(@PathVariable("taskid") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>("Task by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        taskService.deleteTask(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Task has been deleted.");
    }
}
