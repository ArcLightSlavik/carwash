package ua.slavik.carwash.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CarDTO
{
    private Long id;
    private String registrationNumber;
    private String model;
    private List<Long> jobIds;
    private Long customerId;
}

