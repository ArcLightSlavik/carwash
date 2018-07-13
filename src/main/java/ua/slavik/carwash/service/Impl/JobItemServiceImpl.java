package ua.slavik.carwash.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.slavik.carwash.model.JobItem;
import ua.slavik.carwash.repository.JobItemRepository;
import ua.slavik.carwash.service.JobItemService;

@org.springframework.stereotype.Service

public class JobItemServiceImpl implements JobItemService {
    private final JobItemRepository jobItemRepository;

    @Autowired
    public JobItemServiceImpl(JobItemRepository jobItemRepository) {
        this.jobItemRepository = jobItemRepository;
    }

    @Override
    public JobItem getJobItemById(Long id) {
        return jobItemRepository.findById(id).orElse(null);
    }

    @Override
    public JobItem createJobItem(JobItem jobItem) {
        return jobItemRepository.save(jobItem);
    }

    @Override
    public JobItem updateJobItem(JobItem jobItem) {
        return jobItemRepository.save(jobItem);
    }

    @Override
    public void deleteJobItem(Long id) {
        JobItem jobItem = getJobItemById(id);
        if (jobItem != null) {
            jobItemRepository.deleteById(id);
        }
    }
}