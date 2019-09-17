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
public class UpdateEmployeeDTO {
    private Long id;
    @Age
    private Long age;
    @StringField(message = "Invalid first name.")
    private String firstName;
    @StringField(message = "Invalid last name.")
    private String lastName;
    @PhoneNumber
    private String phoneNumber;
    @Email
    private String email;
}
