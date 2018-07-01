package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.DTO.JobItemDTO.CreateJobItemDTO;
import ua.slavik.carwash.DTO.JobItemDTO.JobItemDTO;
import ua.slavik.carwash.DTO.JobItemDTO.UpdateJobItemDTO;
import ua.slavik.carwash.model.JobItem;
import ua.slavik.carwash.service.JobItemService;


@RestController
@RequestMapping("/jobItem")
public class JobItemController
{
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private JobItemService jobItemService;

    @PostMapping
    public ResponseEntity createJobItem(@RequestBody CreateJobItemDTO jobItemDTO)
    {
        JobItem jobItem = modelMapper.map(jobItemDTO, JobItem.class);
        JobItem savedJobItem = jobItemService.createJobItem(jobItem);

        return new ResponseEntity(modelMapper.map(savedJobItem, JobItemDTO.class), HttpStatus.OK);
    }

    @GetMapping(value = "/{jobItemId}")
    public ResponseEntity getJobItem(@PathVariable("jobItemId") Long id)
    {
        JobItem jobItem = jobItemService.getJobItemById(id);
        if (jobItem == null)
        {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(modelMapper.map(jobItem, JobItemDTO.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateJobItem(@RequestBody UpdateJobItemDTO updateJobItemDTO)
    {
        JobItem jobItem = modelMapper.map(updateJobItemDTO, JobItem.class);
        JobItem updatedJobItem = jobItemService.updateJobItem(jobItem);

        return new ResponseEntity(modelMapper.map(updatedJobItem, JobItemDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{jobItemId}")
    public ResponseEntity delete(@PathVariable("jobItemId") Long id)
    {
        JobItem jobItem = jobItemService.getJobItemById(id);
        if (jobItem == null)
        {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        jobItemService.deleteJobItem(id);
        return new ResponseEntity("deleted", HttpStatus.OK);
    }
}
