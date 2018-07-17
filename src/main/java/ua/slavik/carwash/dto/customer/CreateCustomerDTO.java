package ua.slavik.carwash.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDTO {
    @NotNull(message = "first name can not be null.")
    private String firstName;

    @NotNull(message = "last name can not be null.")
    private String lastName;

    private List<Long> carIds;
    private String phoneNumber;
    private String email;
}
