package ua.slavik.carwash.VO;

import lombok.Data;
import java.util.List;

@Data

public class JobVO
{
    private long id;
    private boolean completed;
    private List<Long> serviceIds;
    private Long carId;
}
