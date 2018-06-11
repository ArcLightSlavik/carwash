package ua.slavik.carwash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.repository.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Optional<Customer> getCustomerById(Long id)
    {
        return customerRepository.findById(id);
    }

    @Override
    public Customer createCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteUser(Long id)
    {
        Optional<Customer> customer = getCustomerById(id);
        if (customer !=  null)
        {
            customerRepository.deleteById(id);
        }
    }


}
