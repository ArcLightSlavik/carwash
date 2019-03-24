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
import ua.slavik.carwash.dto.job.JobDTO;
import ua.slavik.carwash.dto.jobtasklink.CreateJobTaskLinkDTO;
import ua.slavik.carwash.dto.task.TaskDTO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.JobStatus;
import ua.slavik.carwash.model.JobTaskLink;
import ua.slavik.carwash.model.Task;
import ua.slavik.carwash.repository.JobRepository;
import ua.slavik.carwash.repository.JobTaskLinkRepository;
import ua.slavik.carwash.repository.TaskRepository;
import java.util.Collections;
import java.util.Date;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JobTaskLinkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JobTaskLinkRepository jobTaskLinkRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void postJobTaskLink() throws Exception {
        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .id(1L)
                .build();
        mockJob = jobRepository.save(mockJob);

        Task mockTask = Task.builder()
                .name("window cleaning")
                .description("cleaning of window")
                .price(10)
                .duration(20)
                .priority(3)
                .status(JobStatus.IN_PROGRESS)
                .repeatable(false)
                .id(1L)
                .build();
        mockTask = taskRepository.save(mockTask);

        CreateJobTaskLinkDTO mockJobTaskLinkDTO = CreateJobTaskLinkDTO.builder()
                .job(JobDTO.builder()
                        .id(mockJob.getId())
                        .build())
                .tasks(Collections.singletonList(TaskDTO.builder()
                        .id(mockTask.getId())
                        .build()))
                .build();

        String mockJobTaskLinkDTOJSON = objectMapper.writeValueAsString(mockJobTaskLinkDTO);

        RequestBuilder requestBuilder = post("/jobtasklink/")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mockJobTaskLinkDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.job.id").value(mockJobTaskLinkDTO.getJob().getId()))
                .andExpect(jsonPath("$.tasks[0].id").value(mockJobTaskLinkDTO.getTasks().get(0).getId()));
    }

    @Test
    public void getJobTaskLink() throws Exception {
        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .id(1L)
                .build();
        mockJob = jobRepository.save(mockJob);

        Task mockTask = Task.builder()
                .name("window cleaning")
                .description("cleaning of window")
                .price(10)
                .duration(20)
                .priority(3)
                .status(JobStatus.IN_PROGRESS)
                .repeatable(false)
                .id(1L)
                .build();
        mockTask = taskRepository.save(mockTask);

        JobTaskLink jobTaskLink = JobTaskLink.builder()
                .job(mockJob)
                .tasks(Collections.singletonList(mockTask))
                .id(1L)
                .build();
        jobTaskLink = jobTaskLinkRepository.save(jobTaskLink);

        String jobTaskLinkJSON = objectMapper.writeValueAsString(jobTaskLink);

        RequestBuilder requestBuilder = get("/jobtasklink/{id}", 1L)
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(jobTaskLinkJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(jobTaskLink.getId()))
                .andExpect(jsonPath("$.job.id").value(jobTaskLink.getJob().getId()))
                .andExpect(jsonPath("$.tasks[0].id").value(jobTaskLink.getTasks().get(0).getId()));
    }
}