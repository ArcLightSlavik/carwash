package ua.slavik.carwash.VO;

import lombok.Data;

@Data
public class CustomerVO
{
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private CarVO car;

}
