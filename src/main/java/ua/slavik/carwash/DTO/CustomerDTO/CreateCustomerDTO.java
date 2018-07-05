package ua.slavik.carwash.DTO.CustomerDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDTO
{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<Long> carIds;
}
