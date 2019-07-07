package ua.slavik.carwash.service;

import ua.slavik.carwash.model.Job;

public interface JobService {
    Job getJobById(Long id);
    Job createJob(Job job);
    Job updateJob(Job job, Long id);
    void deleteJob(Long id);
}
