package ua.slavik.carwash.service;

import ua.slavik.carwash.model.JobItem;

public interface JobItemService
{
    JobItem getJobItemById(Long id);

    JobItem createJobItem(JobItem jobItem);

    JobItem updateJobItem(JobItem jobItem);

    void deleteJobItem(Long id);
}
