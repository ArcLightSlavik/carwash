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
import ua.slavik.carwash.dto.customer.CreateCustomerDTO;
import ua.slavik.carwash.dto.customer.UpdateCustomerDTO;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.repository.CustomerRepository;
import ua.slavik.carwash.service.Impl.CustomerServiceImpl;
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

    @SpyBean
    private CustomerServiceImpl customerServiceMock;

    @Test
    public void getCustomer() throws Exception {
        Customer mockCustomer = Customer.builder()
                .firstName("John")
                .lastName("Wick")
                .email("john.wick@gmail.com")
                .phoneNumber("045873025")
                .id(1L)
                .build();

        Mockito.when(
                customerServiceMock.getCustomerById(1L)
        ).thenReturn(mockCustomer);

        RequestBuilder requestBuilder = get("/customer/{id}", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockCustomer.getId()))
                .andExpect(jsonPath("$.firstName").value(mockCustomer.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(mockCustomer.getLastName()))
                .andExpect(jsonPath("$.email").value(mockCustomer.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(mockCustomer.getPhoneNumber()));
    }

    @Test
    public void postCustomer() throws Exception {

        CreateCustomerDTO mockCustomerDTO = CreateCustomerDTO.builder()
                .firstName("James")
                .lastName("Bond")
                .email("james.bond@gmail.com")
                .phoneNumber("045093454")
                .build();

        String mockCustomerDTOJSON = objectMapper.writeValueAsString(mockCustomerDTO);

        RequestBuilder requestBuilder = post("/customer/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mockCustomerDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value(mockCustomerDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(mockCustomerDTO.getLastName()))
                .andExpect(jsonPath("$.email").value(mockCustomerDTO.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(mockCustomerDTO.getPhoneNumber()));

    }

    @Test
    public void updateCustomer() throws Exception {
        Customer mockCustomer = Customer.builder()
                .firstName("Benedict")
                .lastName("Cumberbatch")
                .email("benny.d@outlook.com")
                .phoneNumber("059768542")
                .build();
        customerRepository.save(mockCustomer);

        UpdateCustomerDTO customerUpdate = UpdateCustomerDTO.builder()
                .firstName("John")
                .lastName("Watson")
                .email("watson.john@gmail.com")
                .phoneNumber("999999999")
                .id(1L)
                .build();

        RequestBuilder requestBuilder = put("/customer/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(customerUpdate));

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value(customerUpdate.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(customerUpdate.getLastName()))
                .andExpect(jsonPath("$.email").value(customerUpdate.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(customerUpdate.getPhoneNumber()));
    }

    @Test
    public void deleteCustomer() throws Exception {
        Customer mockCustomer = Customer.builder()
                .firstName("Luke")
                .lastName("Skywalker")
                .email("skywalker.l@outlook.com")
                .phoneNumber("0987654321")
                .build();
        customerRepository.save(mockCustomer);

        RequestBuilder requestBuilder = delete("/customer/{id}", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(content().string("Deleted"))
                .andExpect(status().isOk());

    }
}
