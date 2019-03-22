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
import ua.slavik.carwash.dto.car.CreateCarDTO;
import ua.slavik.carwash.dto.car.UpdateCarDTO;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.repository.CarRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getCar() throws Exception {
        Car mockCar = Car.builder()
                .registrationPlate("AA 8448 CB")
                .model("Audi")
                .build();
        mockCar = carRepository.save(mockCar);

        String mockCarJSON = objectMapper.writeValueAsString(mockCar);

        RequestBuilder requestBuilder = get("/car/{id}", mockCar.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mockCarJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockCar.getId()))
                .andExpect(jsonPath("$.registrationPlate").value(mockCar.getRegistrationPlate()))
                .andExpect(jsonPath("$.model").value(mockCar.getModel()));
    }

    @Test
    public void postCar() throws Exception {
        CreateCarDTO mockCarDTO = CreateCarDTO.builder()
                .registrationPlate("AA 8448 CB")
                .model("Audi")
                .build();

        String mockCarDTOJSON = objectMapper.writeValueAsString(mockCarDTO);

        RequestBuilder requestBuilder = post("/car/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mockCarDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.registrationPlate").value(mockCarDTO.getRegistrationPlate()))
                .andExpect(jsonPath("$.model").value(mockCarDTO.getModel()));

    }

    @Test
    public void updateCar() throws Exception {
        Car mockCar = Car.builder()
                .registrationPlate("AA 8448 CB")
                .model("Audi")
                .build();
        mockCar = carRepository.save(mockCar);

        UpdateCarDTO carUpdate = UpdateCarDTO.builder()
                .registrationPlate("AA 9999 CB")
                .model("Bmw")
                .id(mockCar.getId())
                .build();

        RequestBuilder requestBuilder = put("/car/{id}", mockCar.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(carUpdate));

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockCar.getId()))
                .andExpect(jsonPath("$.registrationPlate").value(carUpdate.getRegistrationPlate()))
                .andExpect(jsonPath("$.model").value(carUpdate.getModel()));
    }

    @Test
    public void deleteCar() throws Exception {
        Car mockCar = Car.builder()
                .registrationPlate("AA 8448 CB")
                .model("Audi")
                .id(1L)
                .build();
        mockCar = carRepository.save(mockCar);

        RequestBuilder requestBuilder = delete("/car/{id}", mockCar.getId());

        mockMvc.perform(requestBuilder)
                .andExpect(content().string("Car has been deleted."))
                .andExpect(status().isOk());
    }
}
