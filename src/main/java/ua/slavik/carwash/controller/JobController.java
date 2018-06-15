package ua.slavik.carwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.VO.UpdateJobVO;
import ua.slavik.carwash.assemblers.JobAssembler;
import ua.slavik.carwash.VO.CreateJobVO;
import ua.slavik.carwash.VO.JobVO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.service.JobService;


@RestController
public class JobController
{
    @Autowired
    private JobAssembler jobAssembler;

    @Autowired
    private JobService jobService;

    @RequestMapping(method = RequestMethod.POST)
    public JobVO createJob(@RequestBody CreateJobVO jobVO)
    {
        Job job = jobAssembler.toJob(jobVO);
        Job savedJob = jobService.createJob(job);

        return jobAssembler.toJobVO(savedJob);
    }

    @RequestMapping(value = "/jobs/{id}" , method = RequestMethod.GET)
    public JobVO getJobs(@PathVariable("id") Long id)
    {
        return jobAssembler.toJobVO(jobService.getJobById(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JobVO updateJob(@RequestBody UpdateJobVO updateJobVO)
    {
       Job job = jobAssembler.toJob(updateJobVO);
       Job updateJob = jobService.updateJob(job);

       return jobAssembler.toJobVO(job);
    }

    @RequestMapping(value = "/jobs/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id)
    {
        jobService.deleteJob(id);
    }
}