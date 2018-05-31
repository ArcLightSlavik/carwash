package ua.slavik.carwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.repository.CustomerRepository;
import java.util.Optional;


@RestController
public class CustomerController
{
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(value = "/customers")
    public ResponseEntity createCustomer(@RequestBody Customer customer)
    {
        customerRepository.save(customer);

        return new ResponseEntity(customer , HttpStatus.OK);
    }

    @GetMapping("/customers")
    public Iterable<Customer> getCustomers()
    {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity getCustomer(@PathVariable("id") Long id)
    {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent())
        {
            return new ResponseEntity(customer, HttpStatus.OK);
        }
        return new ResponseEntity("no customer id found" + id , HttpStatus.NOT_FOUND);
    }

    @PutMapping ("/customers/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer)
    {
        customer = customerRepository.save(customer);
        return new ResponseEntity(customer, HttpStatus.OK);
    }

    @DeleteMapping ("/customers/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id)
    {
        boolean customerExists = customerRepository.existsById(id);

        if(customerExists)
        {
            customerRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity("no customer found by id" + id , HttpStatus.NOT_FOUND);
    }
}