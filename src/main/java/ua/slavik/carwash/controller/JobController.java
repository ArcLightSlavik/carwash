package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.model.dto.job.CreateJobDTO;
import ua.slavik.carwash.model.dto.job.JobDTO;
import ua.slavik.carwash.model.dto.job.UpdateJobDTO;
import ua.slavik.carwash.service.JobService;

import javax.validation.Valid;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobController {
    private final ModelMapper modelMapper;
    private final JobService jobService;
    private static final String JOB_NOT_FOUND = "Job by id you entered wasn't found.";

    @PostMapping
    public ResponseEntity createJob(@Valid @RequestBody CreateJobDTO jobDTO) {
        Job job = modelMapper.map(jobDTO, Job.class);
        Job savedJob = jobService.createJob(job);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedJob, JobDTO.class));
    }

    @GetMapping(value = "/{jobId}")
    public ResponseEntity getJob(@PathVariable("jobId") Long id) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(JOB_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(job, JobDTO.class));
    }

    @PutMapping(value = "/{jobId}")
    public ResponseEntity updateJob(@RequestBody UpdateJobDTO updateJobDTO, @PathVariable("jobId") Long id) {
        Job oldJob = modelMapper.map(updateJobDTO, Job.class);
        if (oldJob == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(JOB_NOT_FOUND);
        }
        Job updatedJob = jobService.updateJob(oldJob, id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(updatedJob, JobDTO.class));
    }

    @DeleteMapping(value = "/{jobId}")
    public ResponseEntity deleteJob(@PathVariable("jobId") Long id) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(JOB_NOT_FOUND);
        }
        jobService.deleteJob(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Job has been deleted.");
    }
} 