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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "service_job",
            joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "job_id",  referencedColumnName = "id"))

    private List<Service> services;
    //0 = in progress 1 = complete 2 = cancelled
    private int status;
    private Date startDate;
    private Date endDate;
}
