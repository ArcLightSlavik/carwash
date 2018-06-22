package ua.slavik.carwash.DTO;

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
