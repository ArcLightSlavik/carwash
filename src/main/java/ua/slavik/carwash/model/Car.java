package ua.slavik.carwash.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registrationPlate;
    private String model;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @OneToOne
    @JoinColumn(name = "job_id")
    Job job;
}