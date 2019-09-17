package ua.slavik.carwash.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.model.enums.Status;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private Long duration;
    private Long priority;
    private String name;
    private String description;
    private Status status;
    private boolean repeatable;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
}
