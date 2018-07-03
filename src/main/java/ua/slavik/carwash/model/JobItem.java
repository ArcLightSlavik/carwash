package ua.slavik.carwash.model;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class JobItem
{
    private ModelMapper modelMapper = new ModelMapper();

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
