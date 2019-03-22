package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.customercarlink.CreateCustomerCarLinkDTO;
import ua.slavik.carwash.dto.customercarlink.CustomerCarLinkDTO;
import ua.slavik.carwash.model.CustomerCarLink;
import ua.slavik.carwash.service.CustomerCarLinkService;
import javax.validation.Valid;

@RestController
@RequestMapping("/customercarlink")
@RequiredArgsConstructor
public class CustomerCarLinkController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final CustomerCarLinkService customerCarLinkService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createCustomerCarLink(@Valid @RequestBody CreateCustomerCarLinkDTO customerCarLinkDTO) {
        CustomerCarLink customerCarLink = modelMapper.map(customerCarLinkDTO, CustomerCarLink.class);
        CustomerCarLink savedCustomerCarLink = customerCarLinkService.createCustomerCarLink(customerCarLink);

        return new ResponseEntity<>(modelMapper.map(savedCustomerCarLink, CustomerCarLinkDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{customercarlinkid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getCustomerCarLink(@PathVariable("customercarlinkid") Long id) {
        CustomerCarLink customerCarLink = customerCarLinkService.getCustomerCarLinkById(id);
        if (customerCarLink == null) {
            return new ResponseEntity<>("CustomerCar by id you entered wasn't found.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(customerCarLink, CustomerCarLinkDTO.class), HttpStatus.OK);
    }
}
