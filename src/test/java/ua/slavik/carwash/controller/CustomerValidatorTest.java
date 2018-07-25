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
import ua.slavik.carwash.dto.customer.CreateCustomerDTO;
import java.util.ArrayList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerValidatorTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void customerValidatorTooShortTest() throws Exception {

        CreateCustomerDTO mockCustomerDTO = CreateCustomerDTO.builder()
                .firstName("J")
                .lastName("B")
                .email("james.bond")
                .phoneNumber("0450934")
                .carIds(new ArrayList<>())
                .build();

        String mockCustomerDTOJSON = objectMapper.writeValueAsString(mockCustomerDTO);

        RequestBuilder requestBuilder = post("/customer/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mockCustomerDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value(Matchers.containsInAnyOrder(
                        "Invalid first name.",
                        "Invalid last name.",
                        "Invalid phone number.",
                        "Invalid email."
                )));
    }

    @Test
    public void customerValidatorNullTest() throws Exception {

        CreateCustomerDTO mockCustomerDTO = CreateCustomerDTO.builder()
                .firstName(null)
                .lastName(null)
                .email("james.bond")
                .phoneNumber("0450934")
                .carIds(new ArrayList<>())
                .build();

        String mockCustomerDTOJSON = objectMapper.writeValueAsString(mockCustomerDTO);

        RequestBuilder requestBuilder = post("/customer/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mockCustomerDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value(Matchers.containsInAnyOrder(
                        "Invalid first name.",
                        "Invalid last name.",
                        "Invalid phone number.",
                        "Invalid email."
                )));
    }
}
