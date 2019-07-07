package ua.slavik.carwash.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.model.enums.JobStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startDate;
    private JobStatus status;
    private LocalDateTime endDate;

    @OneToMany
    @JoinColumn(name = "task_id")
    List<Task> task;
}
