package ua.slavik.carwash.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.repository.JobRepository;
import ua.slavik.carwash.service.JobItemService;
import ua.slavik.carwash.service.JobService;

@Service
public class JobServiceImpl implements JobService {
    private final ModelMapper modelMapper = new ModelMapper();

    private final JobRepository jobRepository;

    private final JobItemService jobItemService;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, JobItemService jobItemService) {
        this.jobRepository = jobRepository;
        this.jobItemService = jobItemService;
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        Job job = getJobById(id);
        if (job != null) {
            jobRepository.deleteById(id);
        }
    }
    /*
    @Override
    public ResponseEntity addJobItemToJob(Long jobId, Long jobItemId) {
        Job job = getJobById(jobId);
        if (job == null) {
            return new ResponseEntity<>("no job was found by this id", HttpStatus.NOT_FOUND);
        }
        JobItem jobItem = jobItemService.getJobItemById(jobItemId);
        if (jobItem == null) {
            return new ResponseEntity<>("no service was found by this id", HttpStatus.NOT_FOUND);
        }
        boolean allowed = job.getJobItems()
                .stream()
                .allMatch(ji ->
                {
                    if (ji.getStatus() != JobStatus.COMPLETED) {
                        return true;
                    } else {
                        if (ji.isRepeatable() && ji.getPriority() < jobItem.getPriority()) {
                            return true;
                        }
                        return ji.getPriority() == jobItem.getPriority() && ji.getName().equals(jobItem.getName()) && ji.isRepeatable();

                    }

                });
        if (!allowed) {
            return new ResponseEntity<>("cannot add a new jobitem to this job - jobitem with lower priority is already complete", HttpStatus.NOT_ACCEPTABLE);
        }

        List<JobItem> jobItems = job.getJobItems();
        jobItems.add(jobItem);

        jobItems = jobItems
                .stream()
                .sorted(Comparator.comparing(JobItem::getPriority))
                .collect(Collectors.toList());

        job.setJobItems(jobItems);
        updateJob(job);

        return new ResponseEntity<>(modelMapper.map(job, JobDTO.class), HttpStatus.OK);


    }
    */
}
