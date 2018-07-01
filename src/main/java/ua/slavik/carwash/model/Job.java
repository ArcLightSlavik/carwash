package ua.slavik.carwash.model;

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
    private Date startDate;
    private Date endDate;
    private JobStatus status;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(mappedBy = "job")
    private List<JobItem> jobItems;
}
