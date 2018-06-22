package ua.slavik.carwash.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
public class Customer
{
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Car> cars;

}
