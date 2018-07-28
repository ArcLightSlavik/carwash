package ua.slavik.carwash.dto.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.model.JobStatus;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private JobStatus status;
}
