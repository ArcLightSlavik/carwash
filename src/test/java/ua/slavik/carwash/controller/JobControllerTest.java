package ua.slavik.carwash.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ua.slavik.carwash.dto.job.CreateJobDTO;
import ua.slavik.carwash.dto.job.UpdateJobDTO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.JobStatus;
import ua.slavik.carwash.repository.JobRepository;
import java.util.Date;
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
    public void getJob() throws Exception {
        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .id(1L)
                .build();
        mockJob = jobRepository.save(mockJob);

        String mockJobJSON = objectMapper.writeValueAsString(mockJob);

        RequestBuilder requestBuilder = get("/job/{id}", mockJob.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mockJobJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockJob.getId()))
                .andExpect(jsonPath("$.status").value(mockJob.getStatus().toString()));
    }

    @Test
    public void postJob() throws Exception {
        CreateJobDTO mockJobDTO = CreateJobDTO.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .build();

        String mockJobDTOJSON = objectMapper.writeValueAsString(mockJobDTO);

        RequestBuilder requestBuilder = post("/job/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mockJobDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value(mockJobDTO.getStatus().toString()));

    }

    @Test
    public void updateJob() throws Exception {
        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .build();
        mockJob = jobRepository.save(mockJob);

        UpdateJobDTO jobUpdate = UpdateJobDTO.builder()
                .startDate(new Date(1531358161L))
                .endDate(new Date(1531358169L))
                .status(JobStatus.COMPLETED)
                .id(mockJob.getId())
                .build();

        RequestBuilder requestBuilder = put("/job/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(jobUpdate));

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockJob.getId()))
                .andExpect(jsonPath("$.status").value(jobUpdate.getStatus().toString()));
    }

    @Test
    public void deleteJob() throws Exception {
        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .build();
        mockJob = jobRepository.save(mockJob);

        RequestBuilder requestBuilder = delete("/job/{id}", mockJob.getId());

        mockMvc.perform(requestBuilder)
                .andExpect(content().string("Deleted"))
                .andExpect(status().isOk());
    }
}

