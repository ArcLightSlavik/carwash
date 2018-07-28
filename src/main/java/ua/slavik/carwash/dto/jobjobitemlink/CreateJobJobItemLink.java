package ua.slavik.carwash.dto.jobjobitemlink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobJobItemLink {
    private Long jobId;
    private List<Long> jobItemIds;
}
