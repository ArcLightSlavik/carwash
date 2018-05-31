package ua.slavik.carwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.repository.JobRepository;
import java.util.Optional;

@RestController
public class JobController
{
    @Autowired
    private JobRepository jobRepository;

    @PostMapping(value = "/jobs")
    public ResponseEntity createJob(@RequestBody Job job)
    {
        jobRepository.save(job);

        return new ResponseEntity(job , HttpStatus.OK);
    }

    @GetMapping("/jobs")
    public Iterable<Job> getJobs()
    {
        return jobRepository.findAll();
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity getJob(@PathVariable("id") Long id)
    {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent())
        {
            return new ResponseEntity(job, HttpStatus.OK);
        }
        return new ResponseEntity("no job id found" + id , HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity updateJob(@PathVariable Long id, @RequestBody Job job)
    {
        job = jobRepository.save(job);
        return new ResponseEntity(job, HttpStatus.OK);
    }

    @DeleteMapping ("/jobs/{id}")
    public ResponseEntity deleteJob(@PathVariable Long id)
    {
        boolean jobExists = jobRepository.existsById(id);

        if(jobExists)
        {
            jobRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity("no job found by id" + id , HttpStatus.NOT_FOUND);
    }
}