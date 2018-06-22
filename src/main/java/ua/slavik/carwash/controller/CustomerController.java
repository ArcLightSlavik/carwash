package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.DTO.CreateCustomerDTO;
import ua.slavik.carwash.DTO.CustomerDTO;
import ua.slavik.carwash.DTO.UpdateCustomerDTO;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController
{

    private ModelMapper modelMapper =  new ModelMapper();

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST)
    public CustomerDTO createCustomer(@RequestBody CreateCustomerDTO customerVO)
    {
        Customer customer = modelMapper.map(customerVO, Customer.class);
        Customer savedCustomer = customerService.createCustomer(customer);

        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CustomerDTO getCustomer(@PathVariable("id") Long id)
    {
        return modelMapper.map(customerService.getCustomerById(id), CustomerDTO.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CustomerDTO updateCustomer(@RequestBody UpdateCustomerDTO updateCustomerVO)
    {
        Customer customer = modelMapper.map(updateCustomerVO , Customer.class);
        Customer updatedCustomer = customerService.updateCustomer(customer);

        return modelMapper.map(updatedCustomer , CustomerDTO.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id)
    {
        customerService.deleteCustomer(id);
    }
}