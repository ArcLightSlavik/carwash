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
public class CarJobLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany
    @JoinColumn(name = "job_id")
    private List<Job> jobs;
}
