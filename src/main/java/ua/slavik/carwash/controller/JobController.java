package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.DTO.UpdateJobDTO;
import ua.slavik.carwash.DTO.CreateJobDTO;
import ua.slavik.carwash.DTO.JobDTO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.service.JobService;


@RestController
public class JobController
{
    private ModelMapper modelMapper =  new ModelMapper();

    @Autowired
    private JobService jobService;

    @RequestMapping(method = RequestMethod.POST)
    public JobDTO createJob(@RequestBody CreateJobDTO jobVO)
    {
        Job job = modelMapper.map(jobVO , Job.class);
        Job savedJob = jobService.createJob(job);

        return modelMapper.map( savedJob , JobDTO.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getJob(@PathVariable("id") Long id)
    {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(modelMapper.map(job , JobDTO.class), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JobDTO updateJob(@RequestBody UpdateJobDTO updateJobVO)
    {
        Job job = modelMapper.map(updateJobVO , Job.class);
        Job updatedJob = jobService.updateJob(job);

        return modelMapper.map(updatedJob , JobDTO.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        Job job= jobService.getJobById(id);
        if (job == null)
        {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        jobService.deleteJob(id);
        return new ResponseEntity("deleted" , HttpStatus.OK);
    }
}