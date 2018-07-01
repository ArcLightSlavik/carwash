package ua.slavik.carwash.model;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
public class Car
{
    @Id
    @GeneratedValue
    private long id;
    private String registrationNumber;
    private String model;

    @OneToMany(mappedBy = "car")
    private List<Job> jobs;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}