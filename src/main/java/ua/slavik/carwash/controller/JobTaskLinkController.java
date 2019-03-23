package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.jobtasklink.CreateJobTaskLinkDTO;
import ua.slavik.carwash.dto.jobtasklink.JobTaskLinkDTO;
import ua.slavik.carwash.model.JobTaskLink;
import ua.slavik.carwash.service.JobTaskLinkService;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/jobtasklink")
@RequiredArgsConstructor
public class JobTaskLinkController {
    private final ModelMapper modelMapper;
    private final JobTaskLinkService jobTaskLinkService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createJobTaskLink(@Valid @RequestBody CreateJobTaskLinkDTO jobTaskLinkDTO) {
        JobTaskLink jobTaskLink = modelMapper.map(jobTaskLinkDTO, JobTaskLink.class);
        JobTaskLink savedJobTaskLink = jobTaskLinkService.createJobTaskLink(jobTaskLink);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedJobTaskLink, JobTaskLinkDTO.class));
    }

    @GetMapping(value = "/{jobtasklinkid}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getJobJobItemLink(@PathVariable("jobtasklinkid") Long id) {
        JobTaskLink jobTaskLink = jobTaskLinkService.getJobTaskLinkById(id);
        if (jobTaskLink == null) {
            return new ResponseEntity<>("JobTask by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(jobTaskLink, JobTaskLinkDTO.class));
    }
}

