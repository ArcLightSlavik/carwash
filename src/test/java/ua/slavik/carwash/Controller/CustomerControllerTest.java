package ua.slavik.carwash.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import ua.slavik.carwash.controller.CustomerController;
import ua.slavik.carwash.model.Customer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import ua.slavik.carwash.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void getCustomer() throws Exception
    {
        Customer mockCustomer = Customer.builder().firstName("John").lastName("Wick")
                .email("john.wick@gmail.com").phoneNumber("04587302576").id(1L).build();

        Mockito.when(
                customerService.getCustomerById(1L)
        ).thenReturn(mockCustomer);

        mockMvc.perform(get("/customer/{id}", 1L))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockCustomer.getId()))
                .andExpect(jsonPath("$.firstName").value(mockCustomer.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(mockCustomer.getLastName()))
                .andExpect(jsonPath("$.email").value(mockCustomer.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(mockCustomer.getPhoneNumber()));
    }
}

