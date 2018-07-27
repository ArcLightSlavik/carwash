package ua.slavik.carwash.dto.customercarlink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerCarLink
{
    private Long customerId;
    private Long carId;
}
