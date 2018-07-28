package ua.slavik.carwash.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.exception.validators.PhoneNumber;
import ua.slavik.carwash.exception.validators.StringField;
import javax.validation.constraints.Email;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDTO {
    @StringField(max = 40, message = "Invalid first name.")
    private String firstName;

    @StringField(max = 40, message = "Invalid last name.")
    private String lastName;

    @PhoneNumber(message = "Invalid phone number.")
    private String phoneNumber;

    @Email(message = "Invalid email.")
    private String email;
}
