package ua.slavik.carwash.DTO.JobItemDTO;

import lombok.Data;

@Data
public class CreateJobItemDTO
{
    private int price;
    private int duration;
    private int priority;
    private String name;
    private String description;
    private Long jobId;
}
