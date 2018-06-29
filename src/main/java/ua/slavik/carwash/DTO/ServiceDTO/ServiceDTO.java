package ua.slavik.carwash.DTO.ServiceDTO;

import lombok.Data;
import java.util.List;

@Data
public class ServiceDTO
{
    private Long id;
    private List<Long> subJobIds;
    private String name;
    private int price;
    private int duration;
    private String description;
}
