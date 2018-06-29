package ua.slavik.carwash.DTO.ServiceDTO;

import lombok.Data;
import java.util.List;

@Data
public class CreateServiceDTO
{
    private String name;
    private String description;
    private int price;
    private int duration;
    private int priority;
    private List<Long> subJobIds;
}
