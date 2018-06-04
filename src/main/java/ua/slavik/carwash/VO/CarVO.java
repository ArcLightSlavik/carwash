package ua.slavik.carwash.VO;

import lombok.Data;
import ua.slavik.carwash.model.Customer;

import java.util.List;

@Data
public class CarVO
{
    private Long id;
    private String registrationNumber;
    private String model;
    private List<Long> jobIds;
    private Long customerID;
}

