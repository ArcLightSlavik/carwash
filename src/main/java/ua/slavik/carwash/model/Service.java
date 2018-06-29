package ua.slavik.carwash.model;

//what they can perform on the car

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
public class Service
{
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "service")
    private List<SubJob> subJobs;
    private String name;
    private String description;
    private int price;
    private int duration;
    private int priority;
}
