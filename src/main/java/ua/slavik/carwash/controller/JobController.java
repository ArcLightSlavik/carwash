package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.DTO.JobDTO.CreateJobDTO;
import ua.slavik.carwash.DTO.JobDTO.UpdateJobDTO;
import ua.slavik.carwash.DTO.JobDTO.JobDTO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.JobItem;
import ua.slavik.carwash.model.JobStatus;
import ua.slavik.carwash.service.JobItemService;
import ua.slavik.carwash.service.JobService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/job")
public class JobController
{
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private JobService jobService;

    @Autowired
    private JobItemService jobItemService;

    @PostMapping
    public ResponseEntity createJob(@RequestBody CreateJobDTO jobDTO)
    {
        Job job = modelMapper.map(jobDTO, Job.class);
        Job savedJob = jobService.createJob(job);

        return new ResponseEntity(modelMapper.map(savedJob, Job.class), HttpStatus.OK);
    }

    @GetMapping(value = "/{jobId}")
    public ResponseEntity getJob(@PathVariable("jobId") Long id)
    {
        Job job = jobService.getJobById(id);
        if (job == null)
        {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(modelMapper.map(job, JobDTO.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateJob(@RequestBody UpdateJobDTO updateJobDTO)
    {
        Job job = modelMapper.map(updateJobDTO, Job.class);
        Job updatedJob = jobService.updateJob(job);

        return new ResponseEntity(modelMapper.map(updatedJob, Job.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{jobId}")
    public ResponseEntity delete(@PathVariable("jobId") Long id)
    {
        Job job = jobService.getJobById(id);
        if (job == null)
        {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        jobService.deleteJob(id);
        return new ResponseEntity("deleted", HttpStatus.OK);
    }

    @PutMapping(value = "/{jobId}/jobItem/{jobItemId}")
    public ResponseEntity addJobItemToJob(@PathVariable("jobId") Long jobId, @PathVariable("jobItemId") Long jobItemId)
    {
        Job job = jobService.getJobById(jobId);
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
                    }
                    else
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

        jobService.updateJob(job);

        return new ResponseEntity(modelMapper.map(job, JobDTO.class), HttpStatus.OK);

    }

}