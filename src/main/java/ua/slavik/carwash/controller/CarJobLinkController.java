package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.carjoblink.CarJobLinkDTO;
import ua.slavik.carwash.dto.carjoblink.CreateCarJobLinkDTO;
import ua.slavik.carwash.model.CarJobLink;
import ua.slavik.carwash.service.CarJobLinkService;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/carjoblink")
@RequiredArgsConstructor
public class CarJobLinkController {
    private final ModelMapper modelMapper;
    private final CarJobLinkService carJobLinkService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createCarJobLink(@Valid @RequestBody CreateCarJobLinkDTO carJobLinkDTO) {
        CarJobLink carJobLink = modelMapper.map(carJobLinkDTO, CarJobLink.class);
        CarJobLink savedCarJobLink = carJobLinkService.createCarJobLink(carJobLink);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedCarJobLink, CarJobLinkDTO.class));
    }

    @GetMapping(value = "/{carjoblinkid}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getCarJobLink(@PathVariable("carjoblinkid") Long id) {
        CarJobLink carJobLink = carJobLinkService.getCarJobLinkById(id);
        if (carJobLink == null) {
            return new ResponseEntity<>("CarJob by id you entered wasn't found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(carJobLink, CarJobLinkDTO.class));
    }
}
