package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Job;
import java.util.Optional;

public interface JobService
{
    Job getJobById(Long id);

    Job createJob(Job job);

    Job updateJob(Job job);

    void deleteJob(Long id);
}
