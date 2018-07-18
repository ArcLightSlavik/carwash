package ua.slavik.carwash.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.exception.validators.PhoneNumber;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerDTO {
    @PhoneNumber(message = "Invalid phone number.")
    private String phoneNumber;

    private Long id;
    private List<Long> carIds;
    private String firstName;
    private String lastName;
    private String email;
}
