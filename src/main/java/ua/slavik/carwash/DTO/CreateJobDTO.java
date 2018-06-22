package ua.slavik.carwash.DTO;

import lombok.Data;
import java.util.List;

@Data
public class CreateJobDTO
{
    private long id;
    private boolean completed;
    private Long carId;
    private List<Long> serviceIds;
}
