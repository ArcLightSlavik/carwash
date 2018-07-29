package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.customercarlink.CreateCustomerCarLinkDTO;
import ua.slavik.carwash.dto.customercarlink.CustomerCarLinkDTO;
import ua.slavik.carwash.dto.customercarlink.UpdateCustomerCarLinkDTO;
import ua.slavik.carwash.model.CustomerCarLink;
import ua.slavik.carwash.service.CustomerCarLinkService;
import javax.validation.Valid;

@RestController
@RequestMapping("/customercarlink")
public class CustomerCarLinkController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final CustomerCarLinkService customerCarLinkService;

    @Autowired
    public CustomerCarLinkController(CustomerCarLinkService customerCarLinkService) {
        this.customerCarLinkService = customerCarLinkService;
    }

    @PostMapping
    public ResponseEntity createCustomerCarLink(@Valid @RequestBody CreateCustomerCarLinkDTO customerCarLinkDTO) {
        CustomerCarLink customerCarLink = modelMapper.map(customerCarLinkDTO, CustomerCarLink.class);
        CustomerCarLink savedCustomerCarLink = customerCarLinkService.createCustomerCarLink(customerCarLink);

        return new ResponseEntity<>(modelMapper.map(savedCustomerCarLink, CustomerCarLinkDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{customerCarLinkId}")
    public ResponseEntity getCustomerCarLink(@PathVariable("customerCarLinkId") Long id) {
        CustomerCarLink customerCarLink = customerCarLinkService.getCustomerCarLinkById(id);
        if (customerCarLink == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelMapper.map(customerCarLink, CustomerCarLinkDTO.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateCustomerCarLink(@RequestBody UpdateCustomerCarLinkDTO updateCustomerCarLinkDTO) {
        CustomerCarLink customerCarLink = modelMapper.map(updateCustomerCarLinkDTO, CustomerCarLink.class);
        CustomerCarLink updatedCustomerCarLink = customerCarLinkService.updateCustomerCarLink(customerCarLink);

        return new ResponseEntity<>(modelMapper.map(updatedCustomerCarLink, CustomerCarLinkDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerCarLinkId}")
    public ResponseEntity delete(@PathVariable("customerCarLinkId") Long id) {
        CustomerCarLink customerCarLink = customerCarLinkService.getCustomerCarLinkById(id);
        if (customerCarLink == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        customerCarLinkService.deleteCustomerCarLink(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
