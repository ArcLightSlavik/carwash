package ua.slavik.carwash.DTO.CarDTO;

import lombok.Data;
import java.util.List;

@Data
public class CreateCarDTO
{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<Long> carIds;
}
