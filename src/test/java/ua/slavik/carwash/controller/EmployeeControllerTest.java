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
import ua.slavik.carwash.dto.employee.CreateEmployeeDTO;
import ua.slavik.carwash.dto.employee.UpdateEmployeeDTO;
import ua.slavik.carwash.model.Employee;
import ua.slavik.carwash.repository.EmployeeRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    public void postEmployee() throws Exception {

        CreateEmployeeDTO mockEmployeeDTO = CreateEmployeeDTO.builder()
                .firstName("James")
                .lastName("Bond")
                .email("james.bond@gmail.com")
                .phoneNumber("045093454")
                .build();

        String mockEmployeeDTOJSON = objectMapper.writeValueAsString(mockEmployeeDTO);

        RequestBuilder requestBuilder = post("/employee/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mockEmployeeDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value(mockEmployeeDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(mockEmployeeDTO.getLastName()))
                .andExpect(jsonPath("$.email").value(mockEmployeeDTO.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(mockEmployeeDTO.getPhoneNumber()));

    }

    @Test
    public void updateEmployee() throws Exception {
        Employee mockEmployee = Employee.builder()
                .firstName("John")
                .lastName("Wick")
                .email("john.wick@gmail.com")
                .phoneNumber("045873025")
                .id(1L)
                .build();
        mockEmployee = employeeRepository.save(mockEmployee);

        UpdateEmployeeDTO employeeUpdate = UpdateEmployeeDTO.builder()
                .firstName("John")
                .lastName("Watson")
                .email("watson.john@gmail.com")
                .phoneNumber("999999999")
                .id(mockEmployee.getId())
                .build();

        RequestBuilder requestBuilder = put("/employee/{id}", mockEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(employeeUpdate));

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockEmployee.getId()))
                .andExpect(jsonPath("$.firstName").value(employeeUpdate.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(employeeUpdate.getLastName()))
                .andExpect(jsonPath("$.email").value(employeeUpdate.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(employeeUpdate.getPhoneNumber()));
    }

    @Test
    public void deleteEmployee() throws Exception {
        Employee mockEmployee = Employee.builder()
                .firstName("Luke")
                .lastName("Skywalker")
                .email("skywalker.l@outlook.com")
                .phoneNumber("0987654321")
                .build();
        mockEmployee = employeeRepository.save(mockEmployee);

        RequestBuilder requestBuilder = delete("/employee/{id}", mockEmployee.getId());

        mockMvc.perform(requestBuilder)
                .andExpect(content().string("Employee has been deleted."))
                .andExpect(status().isOk());

    }
}