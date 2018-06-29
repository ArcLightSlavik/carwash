package ua.slavik.carwash.DTO.CustomerDTO;

import lombok.Data;
import java.util.List;

@Data
public class CreateCustomerDTO
{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<Long> carIds;
}
