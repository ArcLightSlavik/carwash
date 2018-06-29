package ua.slavik.carwash.model;

//what they can perform on the car

import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Builder
@Entity
public class Service
{
    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(mappedBy = "services")
    private List<Job> jobs;

    private String name;
    private int price;
    private int duration;
    private String description;
}
