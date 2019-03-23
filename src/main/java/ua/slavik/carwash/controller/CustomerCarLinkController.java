package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.customercarlink.CreateCustomerCarLinkDTO;
import ua.slavik.carwash.dto.customercarlink.CustomerCarLinkDTO;
import ua.slavik.carwash.model.CustomerCarLink;
import ua.slavik.carwash.service.CustomerCarLinkService;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/customercarlink")
@RequiredArgsConstructor
public class CustomerCarLinkController {
    private final ModelMapper modelMapper;
    private final CustomerCarLinkService customerCarLinkService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createCustomerCarLink(@Valid @RequestBody CreateCustomerCarLinkDTO customerCarLinkDTO) {
        CustomerCarLink customerCarLink = modelMapper.map(customerCarLinkDTO, CustomerCarLink.class);
        CustomerCarLink savedCustomerCarLink = customerCarLinkService.createCustomerCarLink(customerCarLink);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedCustomerCarLink, CustomerCarLinkDTO.class));
    }

    @GetMapping(value = "/{customercarlinkid}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getCustomerCarLink(@PathVariable("customercarlinkid") Long id) {
        CustomerCarLink customerCarLink = customerCarLinkService.getCustomerCarLinkById(id);
        if (customerCarLink == null) {
            return new ResponseEntity<>("CustomerCar by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(customerCarLink, CustomerCarLinkDTO.class));
    }
}
