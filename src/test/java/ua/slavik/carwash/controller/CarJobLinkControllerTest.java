package ua.slavik.carwash.controller;

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
import ua.slavik.carwash.dto.carjoblink.CreateCarJobLinkDTO;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.model.CarJobLink;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.JobStatus;
import ua.slavik.carwash.repository.CarJobLinkRepository;
import ua.slavik.carwash.repository.CarRepository;
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
public class CarJobLinkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarJobLinkRepository carJobLinkRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private JobRepository jobRepository;

    @Test
    public void getCarJobLink() throws Exception {
        Car mockCar = Car.builder()
                .registrationPlate("AA 8448 CB")
                .model("Audi")
                .id(1L)
                .build();
        mockCar = carRepository.save(mockCar);

        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .id(1L)
                .build();
        mockJob = jobRepository.save(mockJob);

        CarJobLink carJobLink = CarJobLink.builder()
                .car(mockCar)
                .jobs(Collections.singletonList(mockJob))
                .id(1L)
                .build();
        carJobLink = carJobLinkRepository.save(carJobLink);

        RequestBuilder requestBuilder = get("/carJobLink/{id}", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carJobLink.getId()))
                .andExpect(jsonPath("$.carId").value(carJobLink.getCar().getId()))
                .andExpect(jsonPath("$.jobIds").value(Collections.singletonList(carJobLink.getJobs().get(0).getId())));
    }

    @Test
    public void postCarJobLink() throws Exception {
        Car mockCar = Car.builder()
                .registrationPlate("AA 8448 CB")
                .model("Audi")
                .id(1L)
                .build();
        mockCar = carRepository.save(mockCar);

        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .id(1L)
                .build();
        mockJob = jobRepository.save(mockJob);

        CreateCarJobLinkDTO mockCarJobLinkDTO = CreateCarJobLinkDTO.builder()
                .carId(mockCar.getId())
                .jobIds(Collections.singletonList(mockJob.getId()))
                .build();

        RequestBuilder requestBuilder = post("/carJobLink/");

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.carId").value(mockCarJobLinkDTO.getCarId()))
                .andExpect(jsonPath("$.jobIds").value(mockCarJobLinkDTO.getJobIds()));
    }
}
