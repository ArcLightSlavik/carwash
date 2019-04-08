package ua.slavik.carwash.model.dto.customer;

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
public class UpdateCustomerDTO {
    private Long id;

    @StringField(max = 40, message = "Invalid first name.")
    private String firstName;

    @StringField(max = 40, message = "Invalid last name.")
    private String lastName;

    @PhoneNumber
    private String phoneNumber;

    @Email(message = "Invalid email.")
    private String email;
}
