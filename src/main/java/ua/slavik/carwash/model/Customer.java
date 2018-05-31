package ua.slavik.carwash.model;

import javax.persistence.*;

@Entity
public class Customer
{
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Customer( String firstName, String lastName, String phoneNumber, Car car)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.car = car;
    }
}
