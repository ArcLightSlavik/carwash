package ua.slavik.carwash.model.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private Long age;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
