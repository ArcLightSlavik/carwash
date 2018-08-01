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
import ua.slavik.carwash.dto.customercarlink.CreateCustomerCarLinkDTO;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.model.CustomerCarLink;
import ua.slavik.carwash.repository.CarRepository;
import ua.slavik.carwash.repository.CustomerCarLinkRepository;
import ua.slavik.carwash.repository.CustomerRepository;
import ua.slavik.carwash.repository.JobJobItemLinkRepository;
import java.util.Collections;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private JobJobItemLinkRepository jobJobItemLinkRepository;

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

        RequestBuilder requestBuilder = get("/CustomerCarLink/{id}", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerCarLink.getId()))
                .andExpect(jsonPath("$.carId").value(customerCarLink.getCustomer().getId()))
                .andExpect(jsonPath("$.jobIds").value(Collections.singletonList(customerCarLink.getCars().get(0).getId())));
    }

    @Test
    public void postCustomerCArLink() throws Exception {
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
                .customerId(mockCustomer.getId())
                .carIds(Collections.singletonList(mockCar.getId()))
                .build();

        RequestBuilder requestBuilder = get("/customerCarLink/{id}", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.carId").value(mockCustomerCarLinkDTO.getCustomerId()))
                .andExpect(jsonPath("$.jobIds").value(Collections.singletonList(mockCustomerCarLinkDTO.getCarIds())));
    }
}
