package ua.slavik.carwash.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.Task;
import ua.slavik.carwash.model.dto.job.JobDTO;
import ua.slavik.carwash.model.dto.task.CreateTaskDTO;
import ua.slavik.carwash.model.dto.task.UpdateTaskDTO;
import ua.slavik.carwash.model.enums.Status;
import ua.slavik.carwash.repository.JobRepository;
import ua.slavik.carwash.repository.TaskRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void postTask() throws Exception {
        JobDTO mockJobDTO = JobDTO.builder()
                .status(Status.IN_PROGRESS)
                .id(5L)
                .build();

        CreateTaskDTO mockTaskDTO = CreateTaskDTO.builder()
                .name("window cleaning")
                .description("cleaning of window")
                .price(10)
                .duration(20)
                .priority(3)
                .status(Status.IN_PROGRESS)
                .repeatable(false)
                .jobId(mockJobDTO.getId())
                .build();

        String mockTaskDTOJSON = objectMapper.writeValueAsString(mockTaskDTO);

        RequestBuilder requestBuilder = post("/task/")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mockTaskDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.name").value(mockTaskDTO.getName()))
                .andExpect(jsonPath("$.description").value(mockTaskDTO.getDescription()))
                .andExpect(jsonPath("$.price").value(mockTaskDTO.getPrice()))
                .andExpect(jsonPath("$.duration").value(mockTaskDTO.getDuration()))
                .andExpect(jsonPath("$.priority").value(mockTaskDTO.getPriority()))
                .andExpect(jsonPath("$.status").value(mockTaskDTO.getStatus().toString()));
    }

    @Test
    public void getTask() throws Exception {
        Task mockTask = Task.builder()
                .name("window cleaning")
                .description("cleaning of window")
                .price(10)
                .duration(20)
                .priority(3)
                .status(Status.IN_PROGRESS)
                .repeatable(false)
                .id(1L)
                .build();
        mockTask = taskRepository.save(mockTask);

        String mockTaskJSON = objectMapper.writeValueAsString(mockTask);

        RequestBuilder requestBuilder = get("/task/{id}", mockTask.getId())
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mockTaskJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockTask.getId()))
                .andExpect(jsonPath("$.name").value(mockTask.getName()))
                .andExpect(jsonPath("$.description").value(mockTask.getDescription()))
                .andExpect(jsonPath("$.price").value(mockTask.getPrice()))
                .andExpect(jsonPath("$.duration").value(mockTask.getDuration()))
                .andExpect(jsonPath("$.priority").value(mockTask.getPriority()))
                .andExpect(jsonPath("$.status").value(mockTask.getStatus().toString()));
    }

    @Test
    public void updateTask() throws Exception {
        Job mockJob = Job.builder()
                .status(Status.IN_PROGRESS)
                .id(5L)
                .build();
        jobRepository.save(mockJob);

        Task mockTask = Task.builder()
                .description("Car wash")
                .name("Car wash")
                .price(600)
                .priority(1)
                .repeatable(false)
                .status(Status.NOT_STARTED)
                .id(5L)
                .job(mockJob)
                .build();
        mockTask = taskRepository.save(mockTask);

        UpdateTaskDTO taskUpdate = UpdateTaskDTO.builder()
                .description("Car windows wash")
                .name("Window wash")
                .price(300)
                .priority(2)
                .repeatable(true)
                .status(Status.IN_PROGRESS)
                .id(5L)
                .jobId(mockJob.getId())
                .build();

        RequestBuilder requestBuilder = put("/task/{id}", mockTask.getId())
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(taskUpdate));

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(taskUpdate.getId()))
                .andExpect(jsonPath("$.name").value(taskUpdate.getName()))
                .andExpect(jsonPath("$.description").value(taskUpdate.getDescription()))
                .andExpect(jsonPath("$.price").value(taskUpdate.getPrice()))
                .andExpect(jsonPath("$.duration").value(taskUpdate.getDuration()))
                .andExpect(jsonPath("$.priority").value(taskUpdate.getPriority()))
                .andExpect(jsonPath("$.status").value(taskUpdate.getStatus().toString()));
    }

    @Test
    public void deleteTask() throws Exception {
        Task mockTask = Task.builder()
                .name("window cleaning")
                .description("cleaning of window")
                .price(10)
                .duration(20)
                .priority(3)
                .status(Status.COMPLETED)
                .repeatable(false)
                .id(1L)
                .build();
        mockTask = taskRepository.save(mockTask);

        RequestBuilder requestBuilder = delete("/task/{id}", mockTask.getId());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}
