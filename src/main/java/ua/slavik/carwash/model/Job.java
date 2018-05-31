package ua.slavik.carwash.model;

//what is going to be performed on the car

import javax.persistence.*;
import java.util.List;


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
    private boolean completed;

    public Job(Car car, List<Service> services , boolean completed)
    {
        this.car = car;
        this.services = services;
        this.completed = completed;
    }

}
