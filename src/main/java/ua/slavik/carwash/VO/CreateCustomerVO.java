package ua.slavik.carwash.VO;

import lombok.Data;

@Data
public class CreateCustomerVO
{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long carId;
}
