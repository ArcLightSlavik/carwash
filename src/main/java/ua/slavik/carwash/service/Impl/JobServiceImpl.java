package ua.slavik.carwash.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.DTO.JobDTO.JobDTO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.JobItem;
import ua.slavik.carwash.model.JobStatus;
import ua.slavik.carwash.repository.JobRepository;
import ua.slavik.carwash.service.JobItemService;
import ua.slavik.carwash.service.JobService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class JobServiceImpl implements JobService
{
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobItemService jobItemService;

    @Override
    public Job getJobById(Long id)
    {
        return jobRepository.findById(id).orElse(null);
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
        Job job = getJobById(id);
        if (job != null)
        {
            jobRepository.deleteById(id);
        }
    }

    @Override
    public ResponseEntity addJobItemToJob(Long jobId, Long jobItemId)
    {
        Job job = getJobById(jobId);
        if (job == null)
        {
            return new ResponseEntity("jobs not found", HttpStatus.NOT_FOUND);
        }
        JobItem jobItem = jobItemService.getJobItemById(jobItemId);
        if (jobItem == null)
        {
            return new ResponseEntity("services not found", HttpStatus.NOT_FOUND);
        }
        boolean allowed = job.getJobItems()
                .stream()
                .allMatch(ji ->
                {
                    // check if this JobItem is ****NOT**** COMPLETED
                    if (ji.getStatus() != JobStatus.COMPLETED)
                    {
                        return true;
                    } else
                    {
                        // check if the *new* JobItem has a lower priority
                        if (ji.isRepeatable() && ji.getPriority() < jobItem.getPriority())
                        {
                            return true;
                        }

                        // if the priorities are the same, check if they are the same jobItem, and then that it's repeatable
                        if (ji.getPriority() == jobItem.getPriority() && ji.getName() == jobItem.getName() && ji.isRepeatable())
                        {
                            return true;
                        }

                    }

                    return false;
                });

        if (!allowed)
        {
            return new ResponseEntity("cannot add a new jobitem to this job - jobitem with lower priority is already complete", HttpStatus.NOT_ACCEPTABLE);
        }

        List<JobItem> jobItems = job.getJobItems();
        jobItems.add(jobItem);

        jobItems = jobItems
                .stream()
                .sorted(Comparator.comparing(ji -> ji.getPriority()))
                .collect(Collectors.toList());

        job.setJobItems(jobItems);

        updateJob(job);

        return new ResponseEntity(modelMapper.map(job, JobDTO.class), HttpStatus.OK);

    }
}
