package ua.slavik.carwash.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import ua.slavik.carwash.DTO.CustomerDTO.CreateCustomerDTO;
import ua.slavik.carwash.controller.CustomerController;
import ua.slavik.carwash.model.Customer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import ua.slavik.carwash.service.CustomerService;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
@WebAppConfiguration
public class CustomerControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerServiceMock;

    @Test
    public void getCustomer() throws Exception {
        Customer mockCustomer = Customer.builder().firstName("John").lastName("Wick")
                .email("john.wick@gmail.com").phoneNumber("04587302576").id(1L).build();

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

        CreateCustomerDTO mockCustomerDTO = CreateCustomerDTO.builder().firstName("James").lastName("Bond")
                .email("007@gmail.com").phoneNumber("04509345435").carIds(new ArrayList<Long>()).build();

        String mockCustomerDTOJSON = objectMapper.writeValueAsString(mockCustomerDTO);
        System.out.println(mockCustomerDTOJSON);

        RequestBuilder requestBuilder = post("/customer/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mockCustomerDTOJSON);

        System.out.println(requestBuilder.toString());

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value(mockCustomerDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(mockCustomerDTO.getLastName()))
                .andExpect(jsonPath("$.email").value(mockCustomerDTO.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(mockCustomerDTO.getPhoneNumber()));

    }
}
