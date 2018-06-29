package ua.slavik.carwash.DTO.ServiceDTO;

import lombok.Data;
import java.util.List;

@Data
public class UpdateServiceDTO
{
    private long id;
    private String name;
    private int price;
    private List<Long> jobIds;
}
