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
import ua.slavik.carwash.model.JobStatus;
import ua.slavik.carwash.model.Service;
import ua.slavik.carwash.service.JobService;
import ua.slavik.carwash.service.ServiceService;

import java.util.List;


@RestController
public class JobController
{
    private ModelMapper modelMapper =  new ModelMapper();

    @Autowired
    private JobService jobService;

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createJob(@RequestBody CreateJobDTO jobDTO)
    {
        Job job = modelMapper.map(jobDTO , Job.class);
        Job savedJob = jobService.createJob(job);

        return new ResponseEntity(modelMapper.map(savedJob , Job.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/job/{jobId}", method = RequestMethod.GET)
    public ResponseEntity getJob(@PathVariable("jobId") Long id)
    {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(modelMapper.map(job , JobDTO.class), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateJob(@RequestBody UpdateJobDTO updateJobDTO)
    {
        Job job = modelMapper.map(updateJobDTO , Job.class);
        Job updatedJob = jobService.updateJob(job);

        return new ResponseEntity(modelMapper.map(updatedJob , Job.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/job/{jobId}", method = RequestMethod.DELETE)
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
    //add service
    @RequestMapping(value = "/job/{jobId}/services" , method = RequestMethod.PUT)
    public ResponseEntity addServiceToJob(@PathVariable("jobId") Long jobId , @RequestBody Long serviceId)
    {
        Job job = jobService.getJobById(jobId);
        if (job == null)
        {
            return new ResponseEntity("jobs not found " , HttpStatus.NOT_FOUND);
        }
        Service service = serviceService.getServiceById(serviceId);
        if (service == null)
        {
            return new ResponseEntity("services not found " , HttpStatus.NOT_FOUND);
        }
        if (job.getStatus() != JobStatus.NOT_STARTED)
        {
            return new ResponseEntity("You can't add a service if job has been started" , HttpStatus.NOT_ACCEPTABLE);
        }

        List<Service> services = job.getServices();
        services.add(service);
        job.setServices(services);

        jobService.updateJob(job);

        return new ResponseEntity(modelMapper.map(job , JobDTO.class) , HttpStatus.OK);

    }

}