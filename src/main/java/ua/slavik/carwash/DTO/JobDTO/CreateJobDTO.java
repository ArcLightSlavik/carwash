package ua.slavik.carwash.DTO.JobDTO;

import lombok.Data;
import ua.slavik.carwash.model.JobStatus;
import java.util.Date;
import java.util.List;

@Data
public class CreateJobDTO
{
    private Long carId;
    private List<Long> jobItemIds;
    private Date startDate;
    private Date endDate;
    private JobStatus status;
}
