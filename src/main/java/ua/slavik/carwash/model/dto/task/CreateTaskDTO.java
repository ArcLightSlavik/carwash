package ua.slavik.carwash.model.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.exception.validators.StringField;
import ua.slavik.carwash.model.enums.Status;

import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDTO {
    @PositiveOrZero(message = "Invalid price.")
    private Long price;
    @PositiveOrZero(message = "Invalid priority.")
    private Long priority;
    @PositiveOrZero(message = "Invalid duration.")
    private Long duration;
    @StringField(message = "Invalid description.")
    private String description;
    @StringField(message = "Invalid Name.")
    private String name;
    private boolean repeatable;
    private Status status;
    private Long jobId;
}
