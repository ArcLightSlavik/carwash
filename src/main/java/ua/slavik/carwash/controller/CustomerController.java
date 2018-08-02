package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.customer.CreateCustomerDTO;
import ua.slavik.carwash.dto.customer.CustomerDTO;
import ua.slavik.carwash.dto.customer.UpdateCustomerDTO;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.service.CustomerService;
import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createCustomer(@Valid @RequestBody CreateCustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = customerService.createCustomer(customer);

        return new ResponseEntity<>(modelMapper.map(savedCustomer, CustomerDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getCustomer(@PathVariable("customerId") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelMapper.map(customer, CustomerDTO.class), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateCustomer(@RequestBody UpdateCustomerDTO updateCustomerDTO) {
        Customer customer = modelMapper.map(updateCustomerDTO, Customer.class);
        Customer updatedCustomer = customerService.updateCustomer(customer);

        return new ResponseEntity<>(modelMapper.map(updatedCustomer, CustomerDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity delete(@PathVariable("customerId") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}