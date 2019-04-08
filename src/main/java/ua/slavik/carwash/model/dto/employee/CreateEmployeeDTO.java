package ua.slavik.carwash.model.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.exception.validators.Age;
import ua.slavik.carwash.exception.validators.PhoneNumber;
import ua.slavik.carwash.exception.validators.StringField;
import javax.validation.constraints.Email;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeDTO {
    @Age
    private int age;

    @StringField(max = 40, message = "Invalid first name.")
    private String firstName;

    @StringField(max = 40, message = "Invalid last name.")
    private String lastName;

    @PhoneNumber
    private String phoneNumber;

    @Email(message = "Invalid email.")
    private String email;
}
