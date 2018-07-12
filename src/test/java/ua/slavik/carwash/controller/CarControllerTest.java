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
import ua.slavik.carwash.dto.car.CreateCarDTO;
import ua.slavik.carwash.dto.car.UpdateCarDTO;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.repository.CarRepository;
import ua.slavik.carwash.repository.CustomerRepository;
import ua.slavik.carwash.service.Impl.CarServiceImpl;
import java.util.ArrayList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CarControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private CarServiceImpl carServiceMock;

    @Test
    public void getCar() throws Exception
    {
        Car mockCar = Car.builder()
                .registrationNumber("AA 8448 CB")
                .model("Audi")
                .id(1L)
                .build();

        Mockito.when(
                carServiceMock.getCarById(1L)
        ).thenReturn(mockCar);

        RequestBuilder requestBuilder = get("/car/{id}", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockCar.getId()))
                .andExpect(jsonPath("$.registrationNumber").value(mockCar.getRegistrationNumber()))
                .andExpect(jsonPath("$.model").value(mockCar.getModel()));
    }

    @Test
    public void postCar() throws Exception
    {
        Customer mockCustomer = Customer.builder()
                .firstName("John")
                .lastName("Wick")
                .email("john.wick@gmail.com")
                .phoneNumber("04587302576")
                .id(1L)
                .build();
        customerRepository.save(mockCustomer);

        CreateCarDTO mockCarDTO = CreateCarDTO.builder()
                .registrationNumber("AA 8448 CB")
                .model("Audi")
                .jobIds(new ArrayList<>())
                .customerId(1L)
                .build();

        String mockCarDTOJSON = objectMapper.writeValueAsString(mockCarDTO);

        RequestBuilder requestBuilder = post("/car/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mockCarDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.registrationNumber").value(mockCarDTO.getRegistrationNumber()))
                .andExpect(jsonPath("$.model").value(mockCarDTO.getModel()));

    }

    @Test
    public void updateCar() throws Exception
    {
        Car mockCar = Car.builder()
                .registrationNumber("AA 8448 CB")
                .model("Audi")
                .build();
        carRepository.save(mockCar);

        UpdateCarDTO carUpdate = UpdateCarDTO.builder()
                .registrationNumber("AA 9999 CB")
                .model("Bmw")
                .id(1L)
                .build();

        RequestBuilder requestBuilder = put("/car/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(carUpdate));

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.registrationNumber").value(carUpdate.getRegistrationNumber()))
                .andExpect(jsonPath("$.model").value(carUpdate.getModel()));
    }

    @Test
    public void deleteCar() throws Exception
    {
        Car mockCar = Car.builder()
                .registrationNumber("AA 8448 CB")
                .model("Audi")
                .id(1L)
                .build();
        carRepository.save(mockCar);

        RequestBuilder requestBuilder = delete("/car/{id}", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(content().string("deleted"))
                .andExpect(status().isOk());

    }
}
