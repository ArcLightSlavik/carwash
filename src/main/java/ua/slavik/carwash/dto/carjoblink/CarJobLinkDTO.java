package ua.slavik.carwash.dto.carjoblink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.dto.car.CarDTO;
import ua.slavik.carwash.dto.job.JobDTO;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarJobLinkDTO {
    private Long id;
    private CarDTO car;
    private List<JobDTO> jobs;
}
