package ua.slavik.carwash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.repository.JobRepository;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService
{
    @Autowired
    private JobRepository jobRepository;

    @Override
    public Optional<Job> getJobById(Long id)
    {
        return jobRepository.findById(id);
    }

    @Override
    public Job createJob(Job job)
    {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job)
    {
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id)
    {
        Optional<Job> job = getJobById(id);
        if (job != null)
        {
            jobRepository.delete(id);
        }
    }
}
