package ua.slavik.carwash.dto.jobtasklink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.dto.job.JobDTO;
import ua.slavik.carwash.dto.task.TaskDTO;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobTaskLinkDTO {
    private JobDTO job;
    private List<TaskDTO> tasks;
}
