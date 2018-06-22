package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.VO.UpdateJobVO;
import ua.slavik.carwash.VO.CreateJobVO;
import ua.slavik.carwash.VO.JobVO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.service.JobService;


@RestController
public class JobController
{
    private ModelMapper modelMapper =  new ModelMapper();

    @Autowired
    private JobService jobService;

    @RequestMapping(method = RequestMethod.POST)
    public JobVO createJob(@RequestBody CreateJobVO jobVO)
    {
        Job job = modelMapper.map(jobVO , Job.class);
        Job savedJob = jobService.createJob(job);

        return modelMapper.map( savedJob , JobVO.class);
    }

    @RequestMapping(value = "/jobs/{id}" , method = RequestMethod.GET)
    public JobVO getJobs(@PathVariable("id") Long id)
    {
        return modelMapper.map(jobService.getJobById(id), JobVO.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JobVO updateJob(@RequestBody UpdateJobVO updateJobVO)
    {
        Job job = modelMapper.map(updateJobVO , Job.class);
        Job updatedJob = jobService.updateJob(job);

        return modelMapper.map(updatedJob , JobVO.class);
    }

    @RequestMapping(value = "/jobs/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id)
    {
        jobService.deleteJob(id);
    }
}