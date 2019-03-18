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
import ua.slavik.carwash.dto.car.CarDTO;
import ua.slavik.carwash.dto.customer.CustomerDTO;
import ua.slavik.carwash.dto.customercarlink.CreateCustomerCarLinkDTO;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.model.CustomerCarLink;
import ua.slavik.carwash.repository.CarRepository;
import ua.slavik.carwash.repository.CustomerCarLinkRepository;
import ua.slavik.carwash.repository.CustomerRepository;
import java.util.Collections;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerCarLinkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerCarLinkRepository customerCarLinkRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getCustomerCarLink() throws Exception {
        Customer mockCustomer = Customer.builder()
                .firstName("Benedict")
                .lastName("Cumberbatch")
                .email("benny.d@outlook.com")
                .phoneNumber("059768542")
                .id(1L)
                .build();
        mockCustomer = customerRepository.save(mockCustomer);

        Car mockCar = Car.builder()
                .registrationPlate("AA 8448 CB")
                .model("Audi")
                .id(1L)
                .build();
        mockCar = carRepository.save(mockCar);

        CustomerCarLink customerCarLink = CustomerCarLink.builder()
                .customer(mockCustomer)
                .cars(Collections.singletonList(mockCar))
                .id(1L)
                .build();
        customerCarLink = customerCarLinkRepository.save(customerCarLink);

        String customerCarLinkJSON = objectMapper.writeValueAsString(customerCarLink);

        RequestBuilder requestBuilder = get("/customercarlink/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(customerCarLinkJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerCarLink.getId()))
                .andExpect(jsonPath("$.customer.id").value(customerCarLink.getCustomer().getId()))
                .andExpect(jsonPath("$.cars[0].id").value(customerCarLink.getCars().get(0).getId()));
    }

    @Test
    public void postCustomerCarLink() throws Exception {
        Customer mockCustomer = Customer.builder()
                .firstName("Benedict")
                .lastName("Cumberbatch")
                .email("benny.d@outlook.com")
                .phoneNumber("059768542")
                .id(1L)
                .build();
        mockCustomer = customerRepository.save(mockCustomer);

        Car mockCar = Car.builder()
                .registrationPlate("AA 8448 CB")
                .model("Audi")
                .id(1L)
                .build();
        mockCar = carRepository.save(mockCar);

        CreateCustomerCarLinkDTO mockCustomerCarLinkDTO = CreateCustomerCarLinkDTO.builder()
                .customer(CustomerDTO.builder()
                        .id(mockCustomer.getId())
                        .build())

                .cars(Collections.singletonList(CarDTO.builder()
                        .id(mockCar.getId())
                        .build()))
                .build();

        String mockCustomerCarLinkDTOJSON = objectMapper.writeValueAsString(mockCustomerCarLinkDTO);

        RequestBuilder requestBuilder = post("/customercarlink/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mockCustomerCarLinkDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customer.id").value(mockCustomerCarLinkDTO.getCustomer().getId()))
                .andExpect(jsonPath("$.cars[0].id").value(mockCustomerCarLinkDTO.getCars().get(0).getId()));
    }
}
