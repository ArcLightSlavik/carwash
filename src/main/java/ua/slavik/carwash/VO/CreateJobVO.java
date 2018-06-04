package ua.slavik.carwash.VO;

import lombok.Data;
import java.util.List;

@Data
public class CreateJobVO
{
    private long id;
    private boolean completed;
    private Long carId;
    private List<Long> serviceIds;
}
