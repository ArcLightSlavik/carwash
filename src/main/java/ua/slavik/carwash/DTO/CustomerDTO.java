package ua.slavik.carwash.DTO;

import lombok.Data;

@Data
public class CustomerDTO
{
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long carId;

}
