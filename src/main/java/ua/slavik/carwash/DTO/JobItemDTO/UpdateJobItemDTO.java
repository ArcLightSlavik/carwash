package ua.slavik.carwash.DTO.JobItemDTO;

import lombok.Data;

@Data
public class UpdateJobItemDTO
{
    private Long id;
    private int price;
    private int duration;
    private int priority;
    private String name;
    private String description;
    private Long jobId;
}
