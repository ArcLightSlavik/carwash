package ua.slavik.carwash.dto.customercarlink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCarLinkDTO
{
    private Long customerId;
    private Long carId;
}
