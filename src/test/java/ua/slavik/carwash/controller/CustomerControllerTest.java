package ua.slavik.carwash.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.model.dto.customer.CreateCustomerDTO;
import ua.slavik.carwash.model.dto.customer.UpdateCustomerDTO;
import ua.slavik.carwash.repository.CustomerRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void postCustomer() throws Exception {

        CreateCustomerDTO mockCustomerDTO = CreateCustomerDTO.builder()
                .firstName("James")
                .lastName("Bond")
                .email("james.bond@gmail.com")
                .phoneNumber("+123123456")
                .build();

        String mockCustomerDTOJSON = objectMapper.writeValueAsString(mockCustomerDTO);

        RequestBuilder requestBuilder = post("/customer/")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mockCustomerDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value(mockCustomerDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(mockCustomerDTO.getLastName()))
                .andExpect(jsonPath("$.email").value(mockCustomerDTO.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(mockCustomerDTO.getPhoneNumber()));
    }

    @Test
    public void getCustomer() throws Exception {
        Customer mockCustomer = Customer.builder()
                .firstName("John")
                .lastName("Wick")
                .email("john.wick@gmail.com")
                .phoneNumber("045873025")
                .id(1L)
                .build();
        mockCustomer = customerRepository.save(mockCustomer);

        String mockCustomerJSON = objectMapper.writeValueAsString(mockCustomer);

        RequestBuilder requestBuilder = get("/customer/{id}", mockCustomer.getId())
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mockCustomerJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockCustomer.getId()))
                .andExpect(jsonPath("$.firstName").value(mockCustomer.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(mockCustomer.getLastName()))
                .andExpect(jsonPath("$.email").value(mockCustomer.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(mockCustomer.getPhoneNumber()));
    }

    @Test
    public void updateCustomer() throws Exception {
        Customer mockCustomer = Customer.builder()
                .firstName("Martin")
                .lastName("Freeman")
                .email("benny.d@outlook.com")
                .phoneNumber("059768542")
                .build();
        mockCustomer = customerRepository.save(mockCustomer);

        UpdateCustomerDTO updatedCustomer = UpdateCustomerDTO.builder()
                .firstName("John")
                .lastName("Watson")
                .email("watson.john@gmail.com")
                .phoneNumber("999999999")
                .id(mockCustomer.getId())
                .build();

        RequestBuilder requestBuilder = put("/customer/{id}", mockCustomer.getId())
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(updatedCustomer));

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockCustomer.getId()))
                .andExpect(jsonPath("$.firstName").value(updatedCustomer.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(updatedCustomer.getLastName()))
                .andExpect(jsonPath("$.email").value(updatedCustomer.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(updatedCustomer.getPhoneNumber()));
    }

    @Test
    public void deleteCustomer() throws Exception {
        Customer mockCustomer = Customer.builder()
                .firstName("Luke")
                .lastName("Sky walker")
                .email("skywalker.l@outlook.com")
                .phoneNumber("0987654321")
                .build();
        mockCustomer = customerRepository.save(mockCustomer);

        RequestBuilder requestBuilder = delete("/customer/{id}", mockCustomer.getId());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}
