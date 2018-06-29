package ua.slavik.carwash.model;

//what is going to be performed on the car

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
public class Job
{
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;

    @OneToMany(mappedBy = "job")
    private List<SubJob> subJobs;

    private Date startDate;
    private Date endDate;

    // overall job status
    private JobStatus status;
}
