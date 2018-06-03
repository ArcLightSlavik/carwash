package ua.slavik.carwash.VO;

import lombok.Data;

@Data
public class UpdateCustomerVO
{
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private CarVO car;

}
