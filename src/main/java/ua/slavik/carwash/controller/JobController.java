package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.job.CreateJobDTO;
import ua.slavik.carwash.dto.job.JobDTO;
import ua.slavik.carwash.dto.job.UpdateJobDTO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.service.JobService;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {
    private final ModelMapper modelMapper;
    private final JobService jobService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createJob(@Valid @RequestBody CreateJobDTO jobDTO) {
        Job job = modelMapper.map(jobDTO, Job.class);
        Job savedJob = jobService.createJob(job);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedJob, JobDTO.class));
    }

    @GetMapping(value = "/{jobid}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getJob(@PathVariable("jobid") Long id) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return new ResponseEntity<>("Job by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(job, JobDTO.class));
    }

    @PutMapping(value = "/{jobid}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateJob(@RequestBody UpdateJobDTO updateJobDTO, @PathVariable("jobid") Long id) {
        Job oldJob = modelMapper.map(updateJobDTO, Job.class);
        if (oldJob == null) {
            return new ResponseEntity<>("Job by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        Job updatedJob = jobService.updateJob(oldJob, id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(updatedJob, JobDTO.class));
    }

    @DeleteMapping(value = "/{jobid}")
    public ResponseEntity deleteJob(@PathVariable("jobid") Long id) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return new ResponseEntity<>("Job by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        jobService.deleteJob(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Job has been deleted.");
    }
} 