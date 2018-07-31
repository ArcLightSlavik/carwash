package ua.slavik.carwash.controller;

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
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.JobItem;
import ua.slavik.carwash.model.JobJobItemLink;
import ua.slavik.carwash.model.JobStatus;
import ua.slavik.carwash.repository.JobItemRepository;
import ua.slavik.carwash.repository.JobRepository;
import ua.slavik.carwash.service.Impl.JobJobItemLinkServiceImpl;
import java.util.Collections;
import java.util.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @SpyBean
    private JobJobItemLinkServiceImpl jobJobItemLinkServiceMock;

    @Test
    public void getJobJobItemLink() throws Exception {
        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .id(1L)
                .build();
        jobRepository.save(mockJob);

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
        jobItemRepository.save(mockJobItem);

        JobJobItemLink jobJobItemLink = JobJobItemLink.builder()
                .job(mockJob)
                .jobItems(Collections.singletonList(mockJobItem))
                .id(1L)
                .build();

        Mockito.when(
                jobJobItemLinkServiceMock.getJobJobItemLinkById(1L)
        ).thenReturn(jobJobItemLink);

        RequestBuilder requestBuilder = get("/jobJobItemLink/{id}", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(jobJobItemLink.getId()))
                .andExpect(jsonPath("$.carId").value(jobJobItemLink.getJob().getId()))
                .andExpect(jsonPath("$.jobIds").value(Collections.singletonList(jobJobItemLink.getJobItems().get(0).getId())));
    }
}