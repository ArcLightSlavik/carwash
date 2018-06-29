package ua.slavik.carwash.DTO.SubJobDTO;

import lombok.Data;
import ua.slavik.carwash.model.JobStatus;

@Data
public class CreateSubJobDTO
{
    private Long jobId;
    private Long serviceId;
    private JobStatus status;
}
