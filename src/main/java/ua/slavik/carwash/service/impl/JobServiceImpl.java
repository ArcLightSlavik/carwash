package ua.slavik.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.enums.Status;
import ua.slavik.carwash.repository.JobRepository;
import ua.slavik.carwash.service.JobService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job, Long id) {
        Job oldJob = getJobById(id);
        if (oldJob == null) {
            return null;
        }

        job.setId(id);

        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        Job job = getJobById(id);
        if (job != null) {
            jobRepository.deleteById(id);
        }
    }

    @Override
    public List<Job> getJobListByStatus(Status status) {
        return jobRepository.findByStatus(status);
    }
}