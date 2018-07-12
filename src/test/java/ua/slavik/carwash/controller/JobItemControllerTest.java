package ua.slavik.carwash.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ua.slavik.carwash.dto.jobItem.CreateJobItemDTO;
import ua.slavik.carwash.dto.jobItem.UpdateJobItemDTO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.JobItem;
import ua.slavik.carwash.model.JobStatus;
import ua.slavik.carwash.repository.JobItemRepository;
import ua.slavik.carwash.repository.JobRepository;
import ua.slavik.carwash.service.Impl.JobItemServiceImpl;
import java.util.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JobItemControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobItemRepository jobItemRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private JobItemServiceImpl jobItemServiceMock;

    @Test
    public void getJobItem() throws Exception
    {
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

        Mockito.when(
                jobItemServiceMock.getJobItemById(1L)
        ).thenReturn(mockJobItem);

        RequestBuilder requestBuilder = get("/jobitem/{id}", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockJobItem.getId()))
                .andExpect(jsonPath("$.name").value(mockJobItem.getName()))
                .andExpect(jsonPath("$.description").value(mockJobItem.getDescription()))
                .andExpect(jsonPath("$.price").value(mockJobItem.getPrice()))
                .andExpect(jsonPath("$.duration").value(mockJobItem.getDuration()))
                .andExpect(jsonPath("$.priority").value(mockJobItem.getPriority()))
                .andExpect(jsonPath("$.status").value(mockJobItem.getStatus().toString()));
    }

    @Test
    public void postJobItem() throws Exception
    {
        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.NOT_STARTED)
                .id(1L)
                .build();
        jobRepository.save(mockJob);

        CreateJobItemDTO mockJobItemDTO = CreateJobItemDTO.builder()
                .name("window cleaning")
                .description("cleaning of window")
                .price(10)
                .duration(20)
                .priority(3)
                .status(JobStatus.IN_PROGRESS)
                .repeatable(false)
                .jobId(1L)
                .build();

        String mockJobItemDTOJSON = objectMapper.writeValueAsString(mockJobItemDTO);

        RequestBuilder requestBuilder = post("/jobitem/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mockJobItemDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(mockJobItemDTO.getName()))
                .andExpect(jsonPath("$.description").value(mockJobItemDTO.getDescription()))
                .andExpect(jsonPath("$.price").value(mockJobItemDTO.getPrice()))
                .andExpect(jsonPath("$.duration").value(mockJobItemDTO.getDuration()))
                .andExpect(jsonPath("$.priority").value(mockJobItemDTO.getPriority()))
                .andExpect(jsonPath("$.status").value(mockJobItemDTO.getStatus().toString()));
    }

    @Test
    public void updateJobItem() throws Exception
    {
        JobItem mockJobItem = JobItem.builder()
                .name("window cleaning")
                .description("cleaning of window")
                .price(10)
                .duration(20)
                .priority(3)
                .status(JobStatus.IN_PROGRESS)
                .repeatable(false)
                .build();
        jobItemRepository.save(mockJobItem);

        UpdateJobItemDTO jobItemUpdate = UpdateJobItemDTO.builder()
                .name("car cleaning")
                .description("cleaning of car")
                .price(50)
                .duration(40)
                .priority(2)
                .status(JobStatus.COMPLETED)
                .repeatable(true)
                .id(1L)
                .build();

        RequestBuilder requestBuilder = put("/jobitem/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(jobItemUpdate));

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(jobItemUpdate.getName()))
                .andExpect(jsonPath("$.description").value(jobItemUpdate.getDescription()))
                .andExpect(jsonPath("$.price").value(jobItemUpdate.getPrice()))
                .andExpect(jsonPath("$.duration").value(jobItemUpdate.getDuration()))
                .andExpect(jsonPath("$.priority").value(jobItemUpdate.getPriority()))
                .andExpect(jsonPath("$.status").value(jobItemUpdate.getStatus().toString()));
    }

    @Test
    public void deleteJobItem() throws Exception
    {
        JobItem mockJobItem = JobItem.builder()
                .name("window cleaning")
                .description("cleaning of window")
                .price(10)
                .duration(20)
                .priority(3)
                .status(JobStatus.COMPLETED)
                .repeatable(false)
                .id(1L)
                .build();
        jobItemRepository.save(mockJobItem);

        RequestBuilder requestBuilder = delete("/jobitem/{id}", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(content().string("deleted"))
                .andExpect(status().isOk());
    }


    //to do - figure out what's the problem with updateJobItem + make a test for addJobItemToJob
}
