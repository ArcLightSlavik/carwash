package ua.slavik.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.exception.custom.NotFoundException;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.enums.Status;
import ua.slavik.carwash.repository.JobRepository;
import ua.slavik.carwash.service.JobService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job, Long id) {
        getJobById(id);
        job.setId(id);

        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        getJobById(id);
        jobRepository.deleteById(id);
    }

    @Override
    public List<Job> getJobListByStatus(Status status) {
        List<Job> jobList = jobRepository.findByStatus(status);
        if (jobList.size() == 0) {
            throw new NotFoundException();
        }
        return jobList;
    }
}