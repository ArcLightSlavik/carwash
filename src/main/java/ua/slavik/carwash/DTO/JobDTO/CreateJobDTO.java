package ua.slavik.carwash.DTO.JobDTO;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class CreateJobDTO
{
    private long id;
    private boolean completed;
    private Long carId;
    private List<Long> serviceIds;
    private Date startDate;
    private Date endDate;
}
