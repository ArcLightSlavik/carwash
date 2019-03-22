package ua.slavik.carwash.dto.carjoblink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.dto.car.CarDTO;
import ua.slavik.carwash.dto.job.JobDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarJobLinkDTO {
    private CarDTO car;
    private JobDTO job;
}
