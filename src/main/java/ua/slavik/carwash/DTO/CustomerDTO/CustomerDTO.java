package ua.slavik.carwash.DTO.CustomerDTO;

import lombok.Data;
import java.util.List;

@Data
public class CustomerDTO
{
    private Long id;
    private List<Long> carIds;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
