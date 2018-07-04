package ua.slavik.carwash.DTO.CarDTO;

import lombok.Data;
import java.util.List;

@Data
public class CreateCarDTO
{
    private String registrationNumber;
    private String model;
    private List<Long> jobIds;
    private Long customerId;
}
