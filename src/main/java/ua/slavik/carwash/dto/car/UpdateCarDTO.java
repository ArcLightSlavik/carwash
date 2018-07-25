package ua.slavik.carwash.dto.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.exception.validators.StringField;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarDTO {
    @StringField(message = "registrationPlate must be a valid string value.")
    private String registrationPlate;

    @StringField(message = "model must be a valid string value.")
    private String model;

    private Long id;
    private Long customerId;
    private List<Long> jobIds;
}
