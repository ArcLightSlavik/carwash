package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.carjoblink.CarJobLinkDTO;
import ua.slavik.carwash.dto.carjoblink.CreateCarJobLinkDTO;
import ua.slavik.carwash.model.CarJobLink;
import ua.slavik.carwash.service.CarJobLinkService;
import javax.validation.Valid;

@RestController
@RequestMapping("/carJobLink")
public class CarJobLinkController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final CarJobLinkService carJobLinkService;

    @Autowired
    public CarJobLinkController(CarJobLinkService carJobLinkService) {
        this.carJobLinkService = carJobLinkService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createCarJobLink(@Valid @RequestBody CreateCarJobLinkDTO carJobLinkDTO) {
        CarJobLink carJobLink = modelMapper.map(carJobLinkDTO, CarJobLink.class);
        CarJobLink savedCarJobLink = carJobLinkService.createCarJobLink(carJobLink);

        return new ResponseEntity<>(modelMapper.map(savedCarJobLink, CarJobLinkDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{carJobLinkId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getCarJobLink(@PathVariable("carJobLinkId") Long id) {
        CarJobLink carJobLink = carJobLinkService.getCarJobLinkById(id);
        if (carJobLink == null) {
            return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(carJobLink, CarJobLinkDTO.class), HttpStatus.OK);
    }
}
