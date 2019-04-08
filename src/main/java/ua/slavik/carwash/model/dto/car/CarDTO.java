package ua.slavik.carwash.model.dto.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.model.dto.customer.CustomerDTO;
import ua.slavik.carwash.model.dto.job.JobDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String registrationPlate;
    private String model;
    private CustomerDTO customer;
    private JobDTO job;
}

