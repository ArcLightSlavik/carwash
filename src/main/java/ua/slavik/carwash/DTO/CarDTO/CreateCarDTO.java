package ua.slavik.carwash.DTO.CarDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarDTO
{
    private String registrationNumber;
    private String model;
    private List<Long> jobIds;
    private Long customerId;
}
