package ua.slavik.carwash.DTO.CarDTO;

import lombok.Data;
import java.util.List;

@Data
public class UpdateCarDTO
{
    private Long id;
    private List<Long> carIds;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
