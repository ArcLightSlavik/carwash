package ua.slavik.carwash.Controller;

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
import ua.slavik.carwash.DTO.CustomerDTO.CreateCustomerDTO;
import ua.slavik.carwash.DTO.CustomerDTO.UpdateCustomerDTO;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.repository.CustomerRepository;
import ua.slavik.carwash.service.Impl.CustomerServiceImpl;
import java.util.ArrayList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private CustomerServiceImpl customerServiceMock;

    @Test
    public void getCustomer() throws Exception
    {
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
    public void postCustomer() throws Exception
    {

        CreateCustomerDTO mockCustomerDTO = CreateCustomerDTO.builder().firstName("James").lastName("Bond")
                .email("007@gmail.com").phoneNumber("04509345435").carIds(new ArrayList<>()).build();

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
    public void updateCustomer() throws Exception
    {
        Customer mockCustomer = Customer.builder().firstName("Benedict").lastName("Cumberbatch")
                .email("benny.d@outlook.com").phoneNumber("05976854234").build();
        customerRepository.save(mockCustomer);

        UpdateCustomerDTO customerUpdate = UpdateCustomerDTO.builder().id(1L).firstName("Sherlock").lastName("Holmes")
                .email("sherl.h@gmail.com").phoneNumber("03598930675").build();

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
    public void deleteCustomer() throws Exception
    {
        Customer mockCustomer = Customer.builder().firstName("Luke").lastName("Skywalker")
                .email("skywalker.l@outlook.com").phoneNumber("098765432101").build();
        customerRepository.save(mockCustomer);

        RequestBuilder requestBuilder = delete("/customer/{id}" , 1L);


        mockMvc.perform(requestBuilder)
                .andExpect(content().string("deleted"))
                .andExpect(status().isOk());

    }
}
