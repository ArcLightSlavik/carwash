package ua.slavik.carwash.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobJobItemLink {
    @OneToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @OneToMany
    @JoinColumn(name = "jobitem_id")
    private List<JobItem> jobItems;
}
