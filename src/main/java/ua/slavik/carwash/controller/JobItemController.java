package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.jobItem.CreateJobItemDTO;
import ua.slavik.carwash.dto.jobItem.JobItemDTO;
import ua.slavik.carwash.dto.jobItem.UpdateJobItemDTO;
import ua.slavik.carwash.model.JobItem;
import ua.slavik.carwash.service.JobItemService;
import javax.validation.Valid;

@RestController
@RequestMapping("/jobItem")
public class JobItemController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final JobItemService jobItemService;

    @Autowired
    public JobItemController(JobItemService jobItemService) {
        this.jobItemService = jobItemService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createJobItem(@Valid @RequestBody CreateJobItemDTO jobItemDTO) {
        JobItem jobItem = modelMapper.map(jobItemDTO, JobItem.class);
        JobItem savedJobItem = jobItemService.createJobItem(jobItem);

        return new ResponseEntity<>(modelMapper.map(savedJobItem, JobItemDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{jobItemId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getJobItem(@PathVariable("jobItemId") Long id) {
        JobItem jobItem = jobItemService.getJobItemById(id);
        if (jobItem == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelMapper.map(jobItem, JobItemDTO.class), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateJobItem(@RequestBody UpdateJobItemDTO updateJobItemDTO) {
        JobItem jobItem = modelMapper.map(updateJobItemDTO, JobItem.class);
        JobItem updatedJobItem = jobItemService.updateJobItem(jobItem);

        return new ResponseEntity<>(modelMapper.map(updatedJobItem, JobItemDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{jobItemId}")
    public ResponseEntity delete(@PathVariable("jobItemId") Long id) {
        JobItem jobItem = jobItemService.getJobItemById(id);
        if (jobItem == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        jobItemService.deleteJobItem(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
