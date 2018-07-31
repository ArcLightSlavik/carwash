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
import ua.slavik.carwash.dto.carjoblink.CreateCarJobLinkDTO;
import ua.slavik.carwash.dto.carjoblink.UpdateCarJobLinkDTO;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.model.CarJobLink;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.JobStatus;
import ua.slavik.carwash.repository.CarJobLinkRepository;
import ua.slavik.carwash.repository.CarRepository;
import ua.slavik.carwash.repository.JobRepository;
import ua.slavik.carwash.service.Impl.CarJobLinkServiceImpl;
import java.util.Collections;
import java.util.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private CarJobLinkServiceImpl carJobLinkServiceMock;

    @Test
    public void getCarJobLink() throws Exception {
        Car mockCar = Car.builder()
                .registrationPlate("AA 8448 CB")
                .model("Audi")
                .id(1L)
                .build();
        carRepository.save(mockCar);

        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .id(1L)
                .build();
        jobRepository.save(mockJob);

        CarJobLink carJobLink = CarJobLink.builder()
                .car(mockCar)
                .jobs(Collections.singletonList(mockJob))
                .id(1L)
                .build();

        Mockito.when(
                carJobLinkServiceMock.getCarJobLinkById(1L)
        ).thenReturn(carJobLink);

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
        CreateCarJobLinkDTO mockCarJobLinkDTO = CreateCarJobLinkDTO.builder()
                .carId(1L)
                .jobIds(Collections.singletonList(1L))
                .build();

        String mockCarJobLinkDTOJSON = objectMapper.writeValueAsString(mockCarJobLinkDTO);

        RequestBuilder requestBuilder = post("/carJobLink/");

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.carId").value(mockCarJobLinkDTO.getCarId()))
                .andExpect(jsonPath("$.jobIds").value(mockCarJobLinkDTO.getJobIds()));
    }

    @Test
    public void updateCarJobLink() throws Exception {
        Car mockCar = Car.builder()
                .registrationPlate("AA 8448 CB")
                .model("Audi")
                .build();
        carRepository.save(mockCar);

        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .id(1L)
                .build();
        jobRepository.save(mockJob);

        CarJobLink mockCarJobLink = CarJobLink.builder()
                .car(mockCar)
                .jobs(Collections.singletonList(mockJob))
                .id(1L)
                .build();
        carJobLinkRepository.save(mockCarJobLink);

        UpdateCarJobLinkDTO carJobLinkUpdate = UpdateCarJobLinkDTO.builder()
                .carId(1L)
                .jobIds(Collections.emptyList())
                .id(1L)
                .build();

        RequestBuilder requestBuilder = put("/carJobLink/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(carJobLinkUpdate));

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.carId").value(carJobLinkUpdate.getCarId()))
                .andExpect(jsonPath("$.jobIds").value(carJobLinkUpdate.getJobIds()));
    }

    @Test
    public void deleteCarJobLink() throws Exception {
        Car mockCar = Car.builder()
                .registrationPlate("AA 8448 CB")
                .model("Audi")
                .id(1L)
                .build();
        carRepository.save(mockCar);

        Job mockJob = Job.builder()
                .startDate(new Date(1531282957L))
                .endDate(new Date(1531282992L))
                .status(JobStatus.IN_PROGRESS)
                .id(1L)
                .build();
        jobRepository.save(mockJob);

        CarJobLink carJobLink = CarJobLink.builder()
                .car(mockCar)
                .jobs(Collections.singletonList(mockJob))
                .id(1L)
                .build();
        carJobLinkRepository.save(carJobLink);

        RequestBuilder requestBuilder = delete("/carJobLink/{id}", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(content().string("Deleted"))
                .andExpect(status().isOk());

    }
}
