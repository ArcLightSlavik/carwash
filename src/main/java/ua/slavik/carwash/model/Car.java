package ua.slavik.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String registrationNumber;
    private String model;

    @OneToMany(mappedBy = "car")
    private List<Job> jobs;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}