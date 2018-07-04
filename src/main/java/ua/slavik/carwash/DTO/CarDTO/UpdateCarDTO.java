package ua.slavik.carwash.DTO.CarDTO;

import lombok.Data;
import java.util.List;

@Data
public class UpdateCarDTO
{
    private Long id;
    private String registrationNumber;
    private String model;
    private List<Long> jobIds;
    private Long customerId;
}
