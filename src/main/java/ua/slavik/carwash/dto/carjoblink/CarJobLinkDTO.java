package ua.slavik.carwash.dto.carjoblink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarJobLinkDTO {
    private List<Long> jobIds;
    private Long carId;
}
