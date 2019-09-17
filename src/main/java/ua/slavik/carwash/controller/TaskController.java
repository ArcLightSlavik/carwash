package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.model.Task;
import ua.slavik.carwash.model.dto.task.CreateTaskDTO;
import ua.slavik.carwash.model.dto.task.TaskDTO;
import ua.slavik.carwash.model.dto.task.UpdateTaskDTO;
import ua.slavik.carwash.model.enums.Status;
import ua.slavik.carwash.service.JobService;
import ua.slavik.carwash.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {
    private final ModelMapper modelMapper;
    private final TaskService taskService;
    private final JobService jobService;
    private static final String TASK_DELETED = "Task by id you entered was deleted.";

    @PostMapping
    public ResponseEntity createTask(@Valid @RequestBody CreateTaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        task.setJob(jobService.getJobById(taskDTO.getJobId()));
        Task savedTask = taskService.createTask(task);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedTask, TaskDTO.class));
    }

    @GetMapping(value = "/{taskId}")
    public ResponseEntity getTask(@PathVariable("taskId") Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(task, TaskDTO.class));
    }

    @PutMapping(value = "/{taskId}")
    public ResponseEntity updateTask(@RequestBody UpdateTaskDTO updateTaskDTO, @PathVariable("taskId") Long id) {
        Task oldTask = modelMapper.map(updateTaskDTO, Task.class);
        oldTask.setJob(jobService.getJobById(updateTaskDTO.getJobId()));
        Task updatedTask = taskService.updateTask(oldTask, id);
        updatedTask.setJob(jobService.getJobById(updateTaskDTO.getJobId()));

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(updatedTask, TaskDTO.class));
    }

    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") Long id) {
        taskService.deleteTask(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(TASK_DELETED);
    }

    @GetMapping(value = "/taskListByStatus")
    public ResponseEntity getTaskListByStatus(@RequestParam Status status) {
        List<Task> taskList = taskService.getTaskListByStatus(status);

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(taskList, new TypeToken<List<TaskDTO>>() {}.getType()));
    }
}
