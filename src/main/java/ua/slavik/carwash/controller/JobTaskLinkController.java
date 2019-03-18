package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.jobtasklink.CreateJobTaskLinkDTO;
import ua.slavik.carwash.dto.jobtasklink.JobTaskLinkDTO;
import ua.slavik.carwash.model.JobTaskLink;
import ua.slavik.carwash.service.JobTaskLinkService;
import javax.validation.Valid;

@RestController
@RequestMapping("/jobTaskLink")
public class JobTaskLinkController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final JobTaskLinkService jobTaskLinkService;

    @Autowired
    public JobTaskLinkController(JobTaskLinkService jobTaskLinkService) {
        this.jobTaskLinkService = jobTaskLinkService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createJobTaskLink(@Valid @RequestBody CreateJobTaskLinkDTO jobTaskLinkDTO) {
        JobTaskLink jobTaskLink = modelMapper.map(jobTaskLinkDTO, JobTaskLink.class);
        JobTaskLink savedJobTaskLink = jobTaskLinkService.createJobTaskLink(jobTaskLink);

        return new ResponseEntity<>(modelMapper.map(savedJobTaskLink, JobTaskLinkDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{jobTaskLinkId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getJobJobItemLink(@PathVariable("jobTaskLinkId") Long id) {
        JobTaskLink jobTaskLink = jobTaskLinkService.getJobTaskLinkById(id);
        if (jobTaskLink == null) {
            return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(jobTaskLink, JobTaskLinkDTO.class), HttpStatus.OK);
    }
}

