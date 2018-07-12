package ua.slavik.carwash.dto.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.model.JobStatus;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO
{
    private Long carId;
    private List<Long> jobItemIds;
    private Date startDate;
    private Date endDate;
    private JobStatus status;
}
