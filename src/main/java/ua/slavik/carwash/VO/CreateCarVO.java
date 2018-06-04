package ua.slavik.carwash.VO;

import lombok.Data;

import java.util.List;

@Data
public class CreateCarVO
{
    private Long id;
    private String registrationNumber;
    private String model;
    private List<Long> jobIds;
    private Long customerId;
}
