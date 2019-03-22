package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
@RequiredArgsConstructor
public class TaskController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final TaskService taskService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createTask(@Valid @RequestBody CreateTaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task savedTask = taskService.createTask(task);

        return new ResponseEntity<>(modelMapper.map(savedTask, TaskDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{taskid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getTask(@PathVariable("taskid") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>("Task by id you entered wasn't found.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(task, TaskDTO.class), HttpStatus.OK);
    }

    @PutMapping(value = "/{taskid}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateTask(@RequestBody UpdateTaskDTO updateTaskDTO, @PathVariable("taskid") Long id) {
        Task oldTask = modelMapper.map(updateTaskDTO, Task.class);
        if (oldTask == null) {
            return new ResponseEntity<>("Task by id you entered wasn't found.", HttpStatus.BAD_REQUEST);
        }
        Task updatedTask = taskService.updateTask(oldTask, id);

        return new ResponseEntity<>(modelMapper.map(updatedTask, TaskDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{taskid}")
    public ResponseEntity deleteTask(@PathVariable("taskid") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>("Task by id you entered wasn't found.", HttpStatus.BAD_REQUEST);
        }
        taskService.deleteTask(id);

        return new ResponseEntity<>("Task has been deleted.", HttpStatus.OK);
    }
}
