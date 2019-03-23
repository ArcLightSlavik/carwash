package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.customer.CreateCustomerDTO;
import ua.slavik.carwash.dto.customer.CustomerDTO;
import ua.slavik.carwash.dto.customer.UpdateCustomerDTO;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.service.CustomerService;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final ModelMapper modelMapper;
    private final CustomerService customerService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createCustomer(@Valid @RequestBody CreateCustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = customerService.createCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedCustomer, CustomerDTO.class));
    }

    @GetMapping(value = "/{customerid}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getCustomer(@PathVariable("customerid") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>("User by id you entered wasn't found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(customer, CustomerDTO.class));
    }

    @PutMapping(value = "/{customerid}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateCustomer(@RequestBody UpdateCustomerDTO updateCustomerDTO, @PathVariable("customerid") Long id) {
        Customer oldCustomer = modelMapper.map(updateCustomerDTO, Customer.class);
        if (oldCustomer == null) {
            return new ResponseEntity<>("User by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        Customer updatedCustomer = customerService.updateCustomer(oldCustomer, id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(updatedCustomer, CustomerDTO.class));
    }

    @DeleteMapping(value = "/{customerid}")
    public ResponseEntity deleteCustomer(@PathVariable("customerid") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>("User by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        customerService.deleteCustomer(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("User has been deleted.");
    }
}