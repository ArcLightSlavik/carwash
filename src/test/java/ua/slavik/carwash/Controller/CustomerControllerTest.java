package ua.slavik.carwash.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import ua.slavik.carwash.controller.CustomerController;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.repository.CustomerRepository;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.nio.cs.Surrogate.is;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void getCustomerTest() throws Exception
    {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Wick");
        customer.setEmail("john.wick@gmail.com");

        given(customerRepository.findById(1L)).willReturn(java.util.Optional.ofNullable(customer));
        mvc.perform(get("/customer/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id", is(1)))
                .andExpect((ResultMatcher) jsonPath("$.firstName", ("John")))
                .andExpect((ResultMatcher) jsonPath("$.lastName", ("Wick")))
                .andExpect((ResultMatcher) jsonPath("$.email", ("john.wick@gmail.com")));
    }

    @Test
    public void personNotFoundTest() throws Exception
    {
        mvc.perform(get("/customer/2").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
    }

    //carControllerTest
}
