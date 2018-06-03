package ua.slavik.carwash.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
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