package ua.slavik.carwash.dto.jobItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.exception.validators.Description;
import ua.slavik.carwash.exception.validators.Duration;
import ua.slavik.carwash.exception.validators.Price;
import ua.slavik.carwash.exception.validators.Priority;
import ua.slavik.carwash.model.JobStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobItemDTO {
    @Description(message = "Invalid description.")
    private String description;

    @Price(message = "Invalid price.")
    private int price;

    @Priority(message = "Invalid priority.")
    private int priority;

    @Duration(message = "Invalid duration.")
    private int duration;

    private Long jobId;
    private String name;
    private boolean repeatable;
    private JobStatus status;
}
