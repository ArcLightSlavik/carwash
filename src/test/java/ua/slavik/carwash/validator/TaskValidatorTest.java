package ua.slavik.carwash.validator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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
import ua.slavik.carwash.model.dto.task.CreateTaskDTO;
import ua.slavik.carwash.model.enums.JobStatus;
import ua.slavik.carwash.repository.JobRepository;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TaskValidatorTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void taskValidatorTooShortTest() throws Exception {
        Job mockJob = Job.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(5))
                .status(JobStatus.NOT_STARTED)
                .id(1L)
                .build();
        jobRepository.save(mockJob);

        CreateTaskDTO mockTaskDTO = CreateTaskDTO.builder()
                .name("w")
                .description("")
                .price(-500)
                .duration(-10)
                .priority(-1)
                .status(JobStatus.IN_PROGRESS)
                .repeatable(false)
                .build();

        String mockTaskDTOJSON = objectMapper.writeValueAsString(mockTaskDTO);

        RequestBuilder requestBuilder = post("/task/")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mockTaskDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value(Matchers.containsInAnyOrder(
                        "Invalid description.",
                        "Invalid price.",
                        "Invalid priority.",
                        "Invalid duration."
                )));
    }

    @Test
    public void taskValidatorNullTest() throws Exception {
        Job mockJob = Job.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(5))
                .status(JobStatus.NOT_STARTED)
                .id(1L)
                .build();
        jobRepository.save(mockJob);

        CreateTaskDTO mockTaskDTO = CreateTaskDTO.builder()
                .name(null)
                .description(null)
                .price(-500)
                .duration(-10)
                .priority(-1)
                .status(JobStatus.IN_PROGRESS)
                .repeatable(false)
                .build();

        String mockTaskDTOJSON = objectMapper.writeValueAsString(mockTaskDTO);

        RequestBuilder requestBuilder = post("/task/")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mockTaskDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value(Matchers.containsInAnyOrder(
                        "Invalid description.",
                        "Invalid price.",
                        "Invalid priority.",
                        "Invalid duration.",
                        "Invalid Name."
                )));
    }
}