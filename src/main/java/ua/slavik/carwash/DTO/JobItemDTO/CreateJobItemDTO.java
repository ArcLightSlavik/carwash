package ua.slavik.carwash.DTO.JobItemDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.model.JobStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobItemDTO
{
    private int price;
    private int duration;
    private int priority;
    private Long jobId;
    private String name;
    private String description;
    private boolean repeatable;
    private JobStatus status;
}
