package ua.slavik.carwash.dto.employee;

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
    private int age;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
