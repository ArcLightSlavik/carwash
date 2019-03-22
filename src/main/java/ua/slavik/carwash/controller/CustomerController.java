package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
@RequiredArgsConstructor
public class CustomerController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createCustomer(@Valid @RequestBody CreateCustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = customerService.createCustomer(customer);

        return new ResponseEntity<>(modelMapper.map(savedCustomer, CustomerDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{customerid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getCustomer(@PathVariable("customerid") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>("User by id you entered wasn't found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(customer, CustomerDTO.class), HttpStatus.OK);
    }

    @PutMapping(value = "/{customerid}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateCustomer(@RequestBody UpdateCustomerDTO updateCustomerDTO, @PathVariable("customerid") Long id) {
        Customer oldCustomer = modelMapper.map(updateCustomerDTO, Customer.class);
        if (oldCustomer == null) {
            return new ResponseEntity<>("User by id you entered wasn't found.", HttpStatus.BAD_REQUEST);
        }
        Customer updatedCustomer = customerService.updateCustomer(oldCustomer, id);

        return new ResponseEntity<>(modelMapper.map(updatedCustomer, CustomerDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerid}")
    public ResponseEntity deleteCustomer(@PathVariable("customerid") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>("User by id you entered wasn't found.", HttpStatus.BAD_REQUEST);
        }
        customerService.deleteCustomer(id);

        return new ResponseEntity<>("User has been deleted.", HttpStatus.OK);
    }
}