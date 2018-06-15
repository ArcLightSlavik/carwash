package ua.slavik.carwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.VO.CreateCustomerVO;
import ua.slavik.carwash.VO.CustomerVO;
import ua.slavik.carwash.VO.UpdateCustomerVO;
import ua.slavik.carwash.assemblers.CustomerAssembler;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController
{

    @Autowired
    private CustomerAssembler customerAssembler;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST)
    public CustomerVO createCustomer(@RequestBody CreateCustomerVO customerVO)
    {
        Customer customer = customerAssembler.toCustomer(customerVO);
        Customer savedCustomer = customerService.createCustomer(customer);

        return customerAssembler.toCustomerVO(savedCustomer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CustomerVO getCustomer(@PathVariable("id") Long id)
    {
        return customerAssembler.toCustomerVO(customerService.getCustomerById(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CustomerVO updateCustomer(@RequestBody UpdateCustomerVO updateCustomerVO)
    {
        Customer customer = customerAssembler.toCustomer(updateCustomerVO);
        Customer updatedCustomer = customerService.updateCustomer(customer);

        return customerAssembler.toCustomerVO(customer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id)
    {
        customerService.deleteCustomer(id);
    }
}