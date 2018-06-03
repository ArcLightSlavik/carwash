package ua.slavik.carwash.VO;

import lombok.Data;
import ua.slavik.carwash.model.Car;
import java.util.List;

@Data
public class CreateJobVO
{
    private long id;
    private Car car;
    private boolean completed;
    private List<ServiceVO> services;
}
