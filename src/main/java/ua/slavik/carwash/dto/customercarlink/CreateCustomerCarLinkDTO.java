package ua.slavik.carwash.dto.customercarlink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.dto.car.CarDTO;
import ua.slavik.carwash.dto.customer.CustomerDTO;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerCarLinkDTO {
    private CustomerDTO customer;
    private List<CarDTO> cars;
}
