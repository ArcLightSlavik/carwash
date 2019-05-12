package ua.slavik.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.model.dto.customer.CreateCustomerDTO;
import ua.slavik.carwash.model.dto.customer.CustomerDTO;
import ua.slavik.carwash.model.dto.customer.UpdateCustomerDTO;
import ua.slavik.carwash.service.CustomerService;
import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {
    private final ModelMapper modelMapper;
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity createCustomer(@Valid @RequestBody CreateCustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = customerService.createCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedCustomer, CustomerDTO.class));
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity getCustomer(@PathVariable("customerId") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>("User by id you entered wasn't found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(customer, CustomerDTO.class));
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity updateCustomer(@RequestBody UpdateCustomerDTO updateCustomerDTO, @PathVariable("customerId") Long id) {
        Customer oldCustomer = modelMapper.map(updateCustomerDTO, Customer.class);
        if (oldCustomer == null) {
            return new ResponseEntity<>("User by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        Customer updatedCustomer = customerService.updateCustomer(oldCustomer, id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(updatedCustomer, CustomerDTO.class));
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable("customerId") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>("User by id you entered wasn't found.", HttpStatus.NOT_FOUND);
        }
        customerService.deleteCustomer(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("User has been deleted.");
    }
}