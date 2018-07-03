package ua.slavik.carwash.model;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Car
{
    private ModelMapper modelMapper = new ModelMapper();

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