package ua.slavik.carwash.DTO.CustomerDTO;

import lombok.Data;

@Data
public class UpdateCustomerDTO
{
    private Long id;
    private Long carId;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
