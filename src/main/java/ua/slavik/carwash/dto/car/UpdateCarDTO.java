package ua.slavik.carwash.dto.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarDTO {
    @NotNull(message = "registrationPlate can not be null.")
    private String registrationPlate;

    @NotNull(message = "model can not be null.")
    private String model;

    private Long id;
    private Long customerId;
    private List<Long> jobIds;
}
