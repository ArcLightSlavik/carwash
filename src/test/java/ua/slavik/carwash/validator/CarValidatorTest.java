package ua.slavik.carwash.validator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ua.slavik.carwash.model.dto.car.CreateCarDTO;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CarValidatorTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void carValidatorTooShortTest() throws Exception {
        CreateCarDTO mockCarDTO = CreateCarDTO.builder()
                .registrationPlate("")
                .model("")
                .build();

        String mockCarDTOJSON = objectMapper.writeValueAsString(mockCarDTO);

        RequestBuilder requestBuilder = post("/car/")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mockCarDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value(Matchers.containsInAnyOrder(
                        "RegistrationPlate must be a valid string value.",
                        "Model must be a valid string value."
                )));
    }

    @Test
    public void carValidatorNullTest() throws Exception {
        CreateCarDTO mockCarDTO = CreateCarDTO.builder()
                .registrationPlate(null)
                .model(null)
                .build();

        String mockCarDTOJSON = objectMapper.writeValueAsString(mockCarDTO);

        RequestBuilder requestBuilder = post("/car/")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mockCarDTOJSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value(Matchers.containsInAnyOrder(
                        "RegistrationPlate must be a valid string value.",
                        "Model must be a valid string value."
                )));
    }
}
