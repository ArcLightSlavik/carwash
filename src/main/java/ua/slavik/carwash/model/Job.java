package ua.slavik.carwash.model;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Job
{
    private ModelMapper modelMapper = new ModelMapper();

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
