package ua.slavik.carwash.model;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;

@Data
@Builder
@Entity
public class JobItem
{
    @Id
    @GeneratedValue
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
