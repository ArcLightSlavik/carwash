package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.VO.CreateCustomerVO;
import ua.slavik.carwash.VO.CustomerVO;
import ua.slavik.carwash.VO.UpdateCustomerVO;
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
    public CustomerVO createCustomer(@RequestBody CreateCustomerVO customerVO)
    {
        Customer customer = modelMapper.map(customerVO, Customer.class);
        Customer savedCustomer = customerService.createCustomer(customer);

        return modelMapper.map(savedCustomer, CustomerVO.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CustomerVO getCustomer(@PathVariable("id") Long id)
    {
        return modelMapper.map(customerService.getCustomerById(id), CustomerVO.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CustomerVO updateCustomer(@RequestBody UpdateCustomerVO updateCustomerVO)
    {
        Customer customer = modelMapper.map(updateCustomerVO , Customer.class);
        Customer updatedCustomer = customerService.updateCustomer(customer);

        return modelMapper.map(updatedCustomer , CustomerVO.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id)
    {
        customerService.deleteCustomer(id);
    }
}