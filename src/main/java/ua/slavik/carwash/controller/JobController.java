package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.DTO.JobDTO.CreateJobDTO;
import ua.slavik.carwash.DTO.JobDTO.JobDTO;
import ua.slavik.carwash.DTO.JobDTO.UpdateJobDTO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.service.JobService;


@RestController
@RequestMapping("/job")
public class JobController
{
    private final ModelMapper modelMapper = new ModelMapper();

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService)
    {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity createJob(@RequestBody CreateJobDTO jobDTO)
    {
        Job job = modelMapper.map(jobDTO, Job.class);
        Job savedJob = jobService.createJob(job);

        return new ResponseEntity<>(modelMapper.map(savedJob, JobDTO.class), HttpStatus.OK);
    }

    @GetMapping(value = "/{jobId}")
    public ResponseEntity getJob(@PathVariable("jobId") Long id)
    {
        Job job = jobService.getJobById(id);
        if (job == null)
        {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelMapper.map(job, JobDTO.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateJob(@RequestBody UpdateJobDTO updateJobDTO)
    {
        Job job = modelMapper.map(updateJobDTO, Job.class);
        Job updatedJob = jobService.updateJob(job);

        return new ResponseEntity<>(modelMapper.map(updatedJob, JobDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{jobId}")
    public ResponseEntity delete(@PathVariable("jobId") Long id)
    {
        Job job = jobService.getJobById(id);
        if (job == null)
        {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        jobService.deleteJob(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping(value = "/{jobId}/jobItem/{jobItemId}")
    public ResponseEntity addJobItemToJob(@PathVariable("jobId") Long jobId, @PathVariable("jobItemId") Long jobItemId)
    {
        return jobService.addJobItemToJob(jobId, jobItemId);
    }

}