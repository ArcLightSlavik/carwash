package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.DTO.CustomerDTO.CreateCustomerDTO;
import ua.slavik.carwash.DTO.CustomerDTO.CustomerDTO;
import ua.slavik.carwash.DTO.CustomerDTO.UpdateCustomerDTO;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController
{
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody CreateCustomerDTO customerDTO)
    {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = customerService.createCustomer(customer);

        return new ResponseEntity(modelMapper.map(savedCustomer, Customer.class), HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity getCustomer(@PathVariable("customerId") Long id)
    {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null)
        {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(modelMapper.map(customer, CustomerDTO.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateCustomer(@RequestBody UpdateCustomerDTO updateCustomerDTO)
    {
        Customer customer = modelMapper.map(updateCustomerDTO, Customer.class);
        Customer updatedCustomer = customerService.updateCustomer(customer);

        return new ResponseEntity(modelMapper.map(updatedCustomer, Customer.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity delete(@PathVariable("customerId") Long id)
    {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null)
        {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        customerService.deleteCustomer(id);
        return new ResponseEntity("deleted", HttpStatus.OK);
    }
}