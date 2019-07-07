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
public class CreateCustomerDTO {
    @StringField(message = "Invalid first name.")
    private String firstName;
    @StringField(message = "Invalid last name.")
    private String lastName;
    @PhoneNumber
    private String phoneNumber;
    @Email
    private String email;
}
