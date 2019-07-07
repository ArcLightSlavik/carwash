package ua.slavik.carwash.model.dto.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.exception.validators.StringField;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarDTO {
    private Long id;
    @StringField(min = 2, max = 10, message = "RegistrationPlate must be a valid string value.")
    private String registrationPlate;
    @StringField(message = "Model must be a valid string value.")
    private String model;
    private Long customerId;
    private Long jobId;
}
