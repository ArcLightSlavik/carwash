package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.jobjobitemlink.CreateJobJobItemLinkDTO;
import ua.slavik.carwash.dto.jobjobitemlink.JobJobItemLinkDTO;
import ua.slavik.carwash.model.JobItem;
import ua.slavik.carwash.model.JobJobItemLink;
import ua.slavik.carwash.service.JobJobItemLinkService;
import javax.validation.Valid;
import java.util.stream.Collectors;

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

        return new ResponseEntity<>(entityToDTO(savedJobJobItemLink), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{jobJobItemLinkId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getJobJobItemLink(@PathVariable("jobJobItemLinkId") Long id) {
        JobJobItemLink jobJobItemLink = jobJobItemLinkService.getJobJobItemLinkById(id);
        if (jobJobItemLink == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entityToDTO(jobJobItemLink), HttpStatus.OK);
    }

    private JobJobItemLinkDTO entityToDTO(JobJobItemLink entity) {
        JobJobItemLinkDTO DTO = modelMapper.map(entity, JobJobItemLinkDTO.class);
        if (entity.getJobItems() != null) {
            DTO.setJobItemIds(
                    entity.getJobItems().stream()
                            .map(JobItem::getId)
                            .collect(Collectors.toList())
            );
        }
        return DTO;
    }
}

