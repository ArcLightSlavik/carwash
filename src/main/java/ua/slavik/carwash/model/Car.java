package ua.slavik.carwash.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Car
{
    @Id
    @GeneratedValue
    private long id;

    private String registrationNumber;
    private String model;

    @OneToOne(mappedBy = "car")
    private Customer customer;

    @OneToMany(mappedBy = "car")
    private List<Job> jobs;

    public Car(String registrationNumber, String model)
    {
        this.registrationNumber = registrationNumber;
        this.model = model;
    }

}