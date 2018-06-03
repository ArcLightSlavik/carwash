package ua.slavik.carwash.VO;

import lombok.Data;
import java.util.List;

@Data
public class UpdateServiceVO
{
    private long id;
    private List<JobVO> jobs;
    private String name;
    private int price;
}
