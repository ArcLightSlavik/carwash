package ua.slavik.carwash.DTO.CustomerDTO;

import lombok.Data;

@Data
public class CreateCustomerDTO
{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long carId;
}
