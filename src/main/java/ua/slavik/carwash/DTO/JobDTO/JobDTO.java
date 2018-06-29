package ua.slavik.carwash.DTO.JobDTO;

import lombok.Data;
import ua.slavik.carwash.model.JobStatus;
import java.util.List;

@Data
public class JobDTO
{
    private Long id;
    private Long carId;
    private List<Long> subJobIds;
    private JobStatus status;
}
