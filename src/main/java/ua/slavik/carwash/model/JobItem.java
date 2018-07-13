package ua.slavik.carwash.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;
    private int duration;
    private int priority;
    private String name;
    private String description;
    private JobStatus status;
    private boolean repeatable;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
}
