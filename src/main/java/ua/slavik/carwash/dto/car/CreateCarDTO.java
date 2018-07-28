package ua.slavik.carwash.dto.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.exception.validators.StringField;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarDTO {
    @StringField(min = 2, max = 10, message = "registrationPlate must be a valid string value.")
    private String registrationPlate;

    @StringField(message = "model must be a valid string value.")
    private String model;
}
