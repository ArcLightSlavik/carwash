package ua.slavik.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
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
