package ua.slavik.carwash.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private int price;
    private int duration;
    private int priority;
    private JobStatus status;
    private boolean repeatable;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
}
