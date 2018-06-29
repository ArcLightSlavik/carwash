package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.DTO.JobDTO.CreateJobDTO;
import ua.slavik.carwash.DTO.JobDTO.UpdateJobDTO;
import ua.slavik.carwash.DTO.JobDTO.JobDTO;
import ua.slavik.carwash.DTO.SubJobDTO.SubJobDTO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.JobStatus;
import ua.slavik.carwash.model.SubJob;
import ua.slavik.carwash.service.JobService;
import ua.slavik.carwash.service.SubJobService;


@RestController
public class JobController
{
    private ModelMapper modelMapper =  new ModelMapper();

    @Autowired
    private JobService jobService;

    @Autowired
    private SubJobService subJobService;

    @PostMapping(value = "/job")
    public ResponseEntity createJob(@RequestBody CreateJobDTO jobDTO)
    {
        Job job = modelMapper.map(jobDTO , Job.class);
        Job savedJob = jobService.createJob(job);

        return new ResponseEntity(modelMapper.map(savedJob , Job.class), HttpStatus.OK);
    }

    @GetMapping(value = "/job/{jobId}")
    public ResponseEntity getJob(@PathVariable("jobId") Long id)
    {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(modelMapper.map(job , JobDTO.class), HttpStatus.OK);
    }

    @PutMapping(value = "/job")
    public ResponseEntity updateJob(@RequestBody UpdateJobDTO updateJobDTO)
    {
        Job job = modelMapper.map(updateJobDTO , Job.class);
        Job updatedJob = jobService.updateJob(job);

        return new ResponseEntity(modelMapper.map(updatedJob , Job.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/job/{jobId}")
    public ResponseEntity delete(@PathVariable("jobId") Long id)
    {
        Job job= jobService.getJobById(id);
        if (job == null)
        {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        jobService.deleteJob(id);
        return new ResponseEntity("deleted" , HttpStatus.OK);
    }

    @PostMapping(value = "/job/{jobId}/subJob")
    public ResponseEntity addServiceToJob(@PathVariable("jobId") Long jobId, @RequestBody CreateJobDTO subJob)
    {
        Job job = jobService.getJobById(jobId);
        if (job == null)
        {
            return new ResponseEntity("invalid job id", HttpStatus.NOT_FOUND);
        }

        // goes through each subjob in the job , if any are completed, don't let them add another
        for (SubJob sj : job.getSubJobs())
        {
            if (sj.getStatus() == JobStatus.COMPLETED)
            {
                return new ResponseEntity("cannot add new subjobs to this job - a subjob is already complete for it", HttpStatus.NOT_ACCEPTABLE);
            }
        }

        // create the new subjob
        subJobService.createSubJob(modelMapper.map(subJob, SubJob.class));

        return new ResponseEntity(modelMapper.map(subJob, SubJobDTO.class), HttpStatus.OK);

    }

}