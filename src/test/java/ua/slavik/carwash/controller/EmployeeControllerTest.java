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
import ua.slavik.carwash.model.Employee;
import ua.slavik.carwash.repository.EmployeeRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getEmployee() throws Exception {
        Employee mockEmployee = Employee.builder()
                .firstName("John")
                .lastName("Wick")
                .email("john.wick@gmail.com")
                .phoneNumber("045873025")
                .id(1L)
                .build();
        mockEmployee = employeeRepository.save(mockEmployee);

        String mockEmployeeJSON = objectMapper.writeValueAsString(mockEmployee);

        RequestBuilder requestBuilder = get("/employee/{id}", mockEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mockEmployeeJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockEmployee.getId()))
                .andExpect(jsonPath("$.firstName").value(mockEmployee.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(mockEmployee.getLastName()))
                .andExpect(jsonPath("$.email").value(mockEmployee.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(mockEmployee.getPhoneNumber()));
    }
}