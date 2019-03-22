package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/carjoblink")
@RequiredArgsConstructor
public class CarJobLinkController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final CarJobLinkService carJobLinkService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createCarJobLink(@Valid @RequestBody CreateCarJobLinkDTO carJobLinkDTO) {
        CarJobLink carJobLink = modelMapper.map(carJobLinkDTO, CarJobLink.class);
        CarJobLink savedCarJobLink = carJobLinkService.createCarJobLink(carJobLink);

        return new ResponseEntity<>(modelMapper.map(savedCarJobLink, CarJobLinkDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{carjoblinkid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getCarJobLink(@PathVariable("carjoblinkid") Long id) {
        CarJobLink carJobLink = carJobLinkService.getCarJobLinkById(id);
        if (carJobLink == null) {
            return new ResponseEntity<>("CarJob by id you entered wasn't found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(carJobLink, CarJobLinkDTO.class), HttpStatus.OK);
    }
}
