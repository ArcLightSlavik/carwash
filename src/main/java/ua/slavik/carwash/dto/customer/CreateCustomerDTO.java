package ua.slavik.carwash.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.exception.validators.PhoneNumber;
import ua.slavik.carwash.exception.validators.StringField;
import javax.validation.constraints.Email;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDTO {
    @StringField(message = "Invalid first name.")
    private String firstName;

    @StringField(message = "Invalid last name.")
    private String lastName;

    @PhoneNumber(message = "Invalid phone number.")
    private String phoneNumber;

    @Email(message = "Invalid email.")
    private String email;

    private List<Long> carIds;
}
