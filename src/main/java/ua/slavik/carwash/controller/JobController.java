package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/jobs/{id}" , method = RequestMethod.GET)
    public JobDTO getJobs(@PathVariable("id") Long id)
    {
        return modelMapper.map(jobService.getJobById(id), JobDTO.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JobDTO updateJob(@RequestBody UpdateJobDTO updateJobVO)
    {
        Job job = modelMapper.map(updateJobVO , Job.class);
        Job updatedJob = jobService.updateJob(job);

        return modelMapper.map(updatedJob , JobDTO.class);
    }

    @RequestMapping(value = "/jobs/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id)
    {
        jobService.deleteJob(id);
    }
}