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
import ua.slavik.carwash.dto.job.JobDTO;
import ua.slavik.carwash.dto.jobItem.JobItemDTO;
import ua.slavik.carwash.dto.jobjobitemlink.CreateJobJobItemLinkDTO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.JobItem;
import ua.slavik.carwash.model.JobJobItemLink;
import ua.slavik.carwash.model.JobStatus;
import ua.slavik.carwash.repository.JobItemRepository;
import ua.slavik.carwash.repository.JobJobItemLinkRepository;
import ua.slavik.carwash.repository.JobRepository;
import java.util.Collections;
import java.util.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JobJobItemLinkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobItemRepository jobItemRepository;

    @Autowired
    private JobJobItemLinkRepository jobJobItemLinkRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getJobJobItemLink() throws Exception {
        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .id(1L)
                .build();
        mockJob = jobRepository.save(mockJob);

        JobItem mockJobItem = JobItem.builder()
                .name("window cleaning")
                .description("cleaning of window")
                .price(10)
                .duration(20)
                .priority(3)
                .status(JobStatus.IN_PROGRESS)
                .repeatable(false)
                .id(1L)
                .build();
        mockJobItem = jobItemRepository.save(mockJobItem);

        JobJobItemLink jobJobItemLink = JobJobItemLink.builder()
                .job(mockJob)
                .jobItems(Collections.singletonList(mockJobItem))
                .id(1L)
                .build();
        jobJobItemLink = jobJobItemLinkRepository.save(jobJobItemLink);

        String jobJobItemLinkJSON = objectMapper.writeValueAsString(jobJobItemLink);

        RequestBuilder requestBuilder = get("/jobJobItemLink/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jobJobItemLinkJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(jobJobItemLink.getId()))
                .andExpect(jsonPath("$.job.id").value(jobJobItemLink.getJob().getId()))
                .andExpect(jsonPath("$.jobItems[0].id").value(jobJobItemLink.getJobItems().get(0).getId()));
    }

    @Test
    public void postJobJobItemLink() throws Exception {
        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .id(1L)
                .build();
        mockJob = jobRepository.save(mockJob);

        JobItem mockJobItem = JobItem.builder()
                .name("window cleaning")
                .description("cleaning of window")
                .price(10)
                .duration(20)
                .priority(3)
                .status(JobStatus.IN_PROGRESS)
                .repeatable(false)
                .id(1L)
                .build();
        mockJobItem = jobItemRepository.save(mockJobItem);

        CreateJobJobItemLinkDTO mockJobJobItemLinkDTO = CreateJobJobItemLinkDTO.builder()
                .job(JobDTO.builder()
                        .id(mockJob.getId())
                        .build())
                .jobItems(Collections.singletonList(JobItemDTO.builder()
                        .id(mockJobItem.getId())
                        .build()))
                .build();

        String mockJobJobItemLinkDTOJSON = objectMapper.writeValueAsString(mockJobJobItemLinkDTO);

        RequestBuilder requestBuilder = post("/jobJobItemLink/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mockJobJobItemLinkDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.job.id").value(mockJobJobItemLinkDTO.getJob().getId()))
                .andExpect(jsonPath("$.jobItems[0].id").value(mockJobJobItemLinkDTO.getJobItems().get(0).getId()));
    }
}