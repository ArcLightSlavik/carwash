package ua.slavik.carwash.DTO.JobDTO;

import lombok.Data;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.model.JobStatus;
import java.util.Date;
import java.util.List;

@Data
public class UpdateJobDTO
{
    private Long id;
    private Car car;
    private List<Long> subJobIds;
    private Date startDate;
    private Date endDate;
    private JobStatus status;
}
