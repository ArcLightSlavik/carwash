package ua.slavik.carwash.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.repository.CustomerRepository;
import java.util.ArrayList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CarValidatorTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void carValidatorTooShortTest() throws Exception {
        Customer mockCustomer = Customer.builder()
                .firstName("FirstName")
                .lastName("Wick")
                .email("john.wick@gmail.com")
                .phoneNumber("04587302555")
                .id(1L)
                .build();
        customerRepository.save(mockCustomer);

        CreateCarDTO mockCarDTO = CreateCarDTO.builder()
                .registrationPlate("")
                .model("")
                .jobIds(new ArrayList<>())
                .customerId(1L)
                .build();

        String mockCarDTOJSON = objectMapper.writeValueAsString(mockCarDTO);

        RequestBuilder requestBuilder = post("/car/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mockCarDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value(Matchers.containsInAnyOrder(
                        "registrationPlate must be a valid string value.",
                        "model must be a valid string value."
                )));
    }

    @Test
    public void carValidatorNullTest() throws Exception {
        Customer mockCustomer = Customer.builder()
                .firstName("FirstName")
                .lastName("Wick")
                .email("john.wick@gmail.com")
                .phoneNumber("04587302555")
                .id(1L)
                .build();
        customerRepository.save(mockCustomer);

        CreateCarDTO mockCarDTO = CreateCarDTO.builder()
                .registrationPlate(null)
                .model(null)
                .jobIds(new ArrayList<>())
                .customerId(1L)
                .build();

        String mockCarDTOJSON = objectMapper.writeValueAsString(mockCarDTO);

        RequestBuilder requestBuilder = post("/car/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mockCarDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value(Matchers.containsInAnyOrder(
                        "registrationPlate must be a valid string value.",
                        "model must be a valid string value."
                )));
    }
}
