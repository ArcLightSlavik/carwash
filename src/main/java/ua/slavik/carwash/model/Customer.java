package ua.slavik.carwash.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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

    public Customer(String john)
    {
        //temporary
    }
    public Customer()
    {
        //temporary
    }
}
