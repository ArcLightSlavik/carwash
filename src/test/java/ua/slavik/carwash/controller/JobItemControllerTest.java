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
import ua.slavik.carwash.dto.jobItem.CreateJobItemDTO;
import ua.slavik.carwash.dto.jobItem.UpdateJobItemDTO;
import ua.slavik.carwash.model.JobItem;
import ua.slavik.carwash.model.JobStatus;
import ua.slavik.carwash.repository.JobItemRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JobItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobItemRepository jobItemRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getJobItem() throws Exception {
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

        String mockJobItemJSON = objectMapper.writeValueAsString(mockJobItem);

        RequestBuilder requestBuilder = get("/jobItem/{id}", mockJobItem.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mockJobItemJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
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
    public void postJobItem() throws Exception {
        CreateJobItemDTO mockJobItemDTO = CreateJobItemDTO.builder()
                .name("window cleaning")
                .description("cleaning of window")
                .price(10)
                .duration(20)
                .priority(3)
                .status(JobStatus.IN_PROGRESS)
                .repeatable(false)
                .build();

        String mockJobItemDTOJSON = objectMapper.writeValueAsString(mockJobItemDTO);

        RequestBuilder requestBuilder = post("/jobItem/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mockJobItemDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
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
    public void updateJobItem() throws Exception {
        JobItem mockJobItem = JobItem.builder()
                .description("Car wash")
                .name("Car wash")
                .price(600)
                .priority(1)
                .repeatable(false)
                .status(JobStatus.NOT_STARTED)
                .build();
        mockJobItem = jobItemRepository.save(mockJobItem);

        UpdateJobItemDTO jobItemUpdate = UpdateJobItemDTO.builder()
                .description("Car windows wash")
                .name("Window wash")
                .price(300)
                .priority(2)
                .repeatable(true)
                .status(JobStatus.IN_PROGRESS)
                .id(mockJobItem.getId())
                .build();

        RequestBuilder requestBuilder = put("/jobItem/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(jobItemUpdate));

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(jobItemUpdate.getId()))
                .andExpect(jsonPath("$.name").value(jobItemUpdate.getName()))
                .andExpect(jsonPath("$.description").value(jobItemUpdate.getDescription()))
                .andExpect(jsonPath("$.price").value(jobItemUpdate.getPrice()))
                .andExpect(jsonPath("$.duration").value(jobItemUpdate.getDuration()))
                .andExpect(jsonPath("$.priority").value(jobItemUpdate.getPriority()))
                .andExpect(jsonPath("$.status").value(jobItemUpdate.getStatus().toString()));
    }

    @Test
    public void deleteJobItem() throws Exception {
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
        mockJobItem = jobItemRepository.save(mockJobItem);

        RequestBuilder requestBuilder = delete("/jobItem/{id}", mockJobItem.getId());

        mockMvc.perform(requestBuilder)
                .andExpect(content().string("Deleted"))
                .andExpect(status().isOk());
    }
}
