package ua.slavik.carwash.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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