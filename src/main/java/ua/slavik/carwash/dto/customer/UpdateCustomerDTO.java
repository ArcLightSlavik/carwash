package ua.slavik.carwash.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.exception.validators.Email;
import ua.slavik.carwash.exception.validators.FirstName;
import ua.slavik.carwash.exception.validators.LastName;
import ua.slavik.carwash.exception.validators.PhoneNumber;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerDTO {
    @FirstName(message = "Invalid first name.")
    private String firstName;

    @LastName(message = "Invalid last name.")
    private String lastName;

    @PhoneNumber(message = "Invalid phone number.")
    private String phoneNumber;

    @Email(message = "Invalid email.")
    private String email;

    private List<Long> carIds;
    private Long id;
}
