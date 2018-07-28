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
public class JobJobItemLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @OneToMany
    @JoinColumn(name = "jobitem_id")
    private List<JobItem> jobItems;
}
