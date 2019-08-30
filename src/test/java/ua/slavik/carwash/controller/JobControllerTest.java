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
import ua.slavik.carwash.model.dto.job.CreateJobDTO;
import ua.slavik.carwash.model.dto.job.UpdateJobDTO;
import ua.slavik.carwash.model.enums.Status;
import ua.slavik.carwash.repository.JobRepository;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JobControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void postJob() throws Exception {
        CreateJobDTO mockJobDTO = CreateJobDTO.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(5))
                .status(Status.IN_PROGRESS)
                .build();

        String mockJobDTOJSON = objectMapper.writeValueAsString(mockJobDTO);

        RequestBuilder requestBuilder = post("/job/")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mockJobDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.status").value(mockJobDTO.getStatus().toString()));
    }

    @Test
    public void getJob() throws Exception {
        Job mockJob = Job.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(5))
                .status(Status.IN_PROGRESS)
                .id(1L)
                .build();
        mockJob = jobRepository.save(mockJob);

        String mockJobJSON = objectMapper.writeValueAsString(mockJob);

        RequestBuilder requestBuilder = get("/job/{id}", mockJob.getId())
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mockJobJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockJob.getId()))
                .andExpect(jsonPath("$.status").value(mockJob.getStatus().toString()));
    }

    @Test
    public void updateJob() throws Exception {
        Job mockJob = Job.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(5))
                .status(Status.IN_PROGRESS)
                .build();
        mockJob = jobRepository.save(mockJob);

        UpdateJobDTO updatedJob = UpdateJobDTO.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(5))
                .status(Status.COMPLETED)
                .id(mockJob.getId())
                .build();

        RequestBuilder requestBuilder = put("/job/{id}", mockJob.getId())
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(updatedJob));

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockJob.getId()))
                .andExpect(jsonPath("$.status").value(updatedJob.getStatus().toString()));
    }

    @Test
    public void deleteJob() throws Exception {
        Job mockJob = Job.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(5))
                .status(Status.IN_PROGRESS)
                .build();
        mockJob = jobRepository.save(mockJob);

        RequestBuilder requestBuilder = delete("/job/{id}", mockJob.getId());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}


