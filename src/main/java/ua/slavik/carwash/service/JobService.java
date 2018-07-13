package ua.slavik.carwash.service;

import org.springframework.http.ResponseEntity;
import ua.slavik.carwash.model.Job;

public interface JobService {
    Job getJobById(Long id);

    Job createJob(Job job);

    Job updateJob(Job job);

    void deleteJob(Long id);

    ResponseEntity addJobItemToJob(Long jobId, Long jobItemId);
}
