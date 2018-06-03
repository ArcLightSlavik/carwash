package ua.slavik.carwash.VO;

import lombok.Data;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.model.Service;

import java.util.List;

@Data

public class UpdateJobVO
{
    private long id;
    private Car car;
    private boolean completed;
    private List<Service> services;
}
