package ua.slavik.carwash.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.repository.CustomerRepository;
import ua.slavik.carwash.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerById(Long id)
    {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id)
    {
        Customer customer = getCustomerById(id);
        if (customer != null)
        {
            customerRepository.deleteById(id);
        }
    }
}
