package ua.slavik.carwash.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.repository.JobRepository;
import ua.slavik.carwash.service.JobItemService;
import ua.slavik.carwash.service.JobService;

@Service
public class JobServiceImpl implements JobService {
    private final ModelMapper modelMapper = new ModelMapper();
    private final JobRepository jobRepository;
    private final JobItemService jobItemService;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, JobItemService jobItemService) {
        this.jobRepository = jobRepository;
        this.jobItemService = jobItemService;
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        Job job = getJobById(id);
        if (job != null) {
            jobRepository.deleteById(id);
        }
    }
}
