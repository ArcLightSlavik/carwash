package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.carjoblink.CarJobLinkDTO;
import ua.slavik.carwash.dto.carjoblink.CreateCarJobLinkDTO;
import ua.slavik.carwash.dto.carjoblink.UpdateCarJobLinkDTO;
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

    @PostMapping
    public ResponseEntity createCarJobLink(@Valid @RequestBody CreateCarJobLinkDTO carJobLinkDTO) {
        CarJobLink carJobLink = modelMapper.map(carJobLinkDTO, CarJobLink.class);
        CarJobLink savedCarJobLink = carJobLinkService.createCarJobLink(carJobLink);

        return new ResponseEntity<>(modelMapper.map(savedCarJobLink, CarJobLinkDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{carJobLinkId}")
    public ResponseEntity getCarJobLink(@PathVariable("carJobLinkId") Long id) {
        CarJobLink carJobLink = carJobLinkService.getCarJobLinkById(id);
        if (carJobLink == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelMapper.map(carJobLink, CarJobLinkDTO.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateCarJobLink(@RequestBody UpdateCarJobLinkDTO updateCarJobLinkDTO) {
        CarJobLink carJobLink = modelMapper.map(updateCarJobLinkDTO, CarJobLink.class);
        CarJobLink updatedCarJobLink = carJobLinkService.updateCarJobLink(carJobLink);

        return new ResponseEntity<>(modelMapper.map(updatedCarJobLink, CarJobLinkDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{carJobLinkId}")
    public ResponseEntity delete(@PathVariable("carJobLinkId") Long id) {
        CarJobLink carJobLink = carJobLinkService.getCarJobLinkById(id);
        if (carJobLink == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        carJobLinkService.deleteCarJobLink(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
