package ua.slavik.carwash.dto.jobjobitemlink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.dto.job.JobDTO;
import ua.slavik.carwash.dto.jobItem.JobItemDTO;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobJobItemLinkDTO {
    private Long id;
    private JobDTO job;
    private List<JobItemDTO> jobItems;
}
