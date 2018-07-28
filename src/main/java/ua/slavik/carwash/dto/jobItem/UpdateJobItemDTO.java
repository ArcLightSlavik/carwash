package ua.slavik.carwash.dto.jobItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.exception.validators.IntegerField;
import ua.slavik.carwash.exception.validators.StringField;
import ua.slavik.carwash.model.JobStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobItemDTO {
    @StringField(message = "Invalid description.")
    private String description;

    @IntegerField(message = "Invalid price.")
    private int price;

    @IntegerField(message = "Invalid priority.")
    private int priority;

    @IntegerField(message = "Invalid duration.")
    private int duration;

    private Long id;
    private String name;
    private boolean repeatable;
    private JobStatus status;
}
