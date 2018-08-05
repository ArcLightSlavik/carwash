package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.jobjobitemlink.CreateJobJobItemLinkDTO;
import ua.slavik.carwash.dto.jobjobitemlink.JobJobItemLinkDTO;
import ua.slavik.carwash.model.JobJobItemLink;
import ua.slavik.carwash.service.JobJobItemLinkService;
import javax.validation.Valid;

@RestController
@RequestMapping("/jobJobItemLink")
public class JobJobItemLinkController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final JobJobItemLinkService jobJobItemLinkService;

    @Autowired
    public JobJobItemLinkController(JobJobItemLinkService jobJobItemLinkService) {
        this.jobJobItemLinkService = jobJobItemLinkService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createJobJobItemLink(@Valid @RequestBody CreateJobJobItemLinkDTO jobJobItemLinkDTO) {
        JobJobItemLink jobJobItemLink = modelMapper.map(jobJobItemLinkDTO, JobJobItemLink.class);
        JobJobItemLink savedJobJobItemLink = jobJobItemLinkService.createJobJobItemLink(jobJobItemLink);

        return new ResponseEntity<>(modelMapper.map(savedJobJobItemLink, JobJobItemLinkDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{jobJobItemLinkId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getJobJobItemLink(@PathVariable("jobJobItemLinkId") Long id) {
        JobJobItemLink jobJobItemLink = jobJobItemLinkService.getJobJobItemLinkById(id);
        if (jobJobItemLink == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelMapper.map(jobJobItemLink, JobJobItemLinkDTO.class), HttpStatus.OK);
    }
}
